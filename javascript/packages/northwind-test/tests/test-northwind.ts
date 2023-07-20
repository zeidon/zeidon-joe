import { ZeidonRestValues } from '@zeidon/rest-client';
import { RestZeidonConfiguration } from '@zeidon/rest-client-node';
import { CommitError } from '@zeidon/core';
import { expect } from 'chai';
import 'mocha';
import { Customer } from '../src/Customer';
import { Employee } from '../src/Employee';
import { Order } from '../src/Order';
import { Product } from '../src/Product';
import { Shipper } from '../src/Shipper';
import * as fs from 'fs';

const REST_VALUES: ZeidonRestValues = {
    restUrl: "http://localhost:8080/zeidon-api/Northwind"
};

// Instantiate a Zeidon config to set up configuration.  Just instantiating
// it is all we need.  Load the config that uses 'got' http library.
const zeidonConfiguration = new RestZeidonConfiguration( REST_VALUES );
const child = require('child_process');
const debugJettyPort = undefined; // If set, start Jetty with debug port open.
const debugJettySuspend = "n";
var jettyProcess

console.log( `cwd = ${__dirname}` );

const loadJson = function(json: string): string {
    return require( __dirname + "/" + json + ".json" );
}

before( () => {
    startJettyServer();
    return new Promise<void>( ( resolve ) => {
        setTimeout( () => {
            console.log( "Done waiting for jetty server to start." );
            resolve();
        }, 1500 );
    } );
} );

after(function() {
    console.log( "Stopping jetty server..." );
    jettyProcess.kill();
});

describe('Load from JSON',
  () => {
    it('should load single root', () => {
      let order = new Order(loadJson('order1'));
      expect(order.Order$.OrderId).to.equal("10248");
    });

    it('should load multiple roots', () => {
      let products = new Product(loadJson('products'));
      expect(products.Product.length).to.equal(77);
    });
  }
);

describe('REST',
  () => {
    it('should activate', async () => {
      let order = await Order.activate( { OrderId: "10248" } );
      expect(order.Order$.OrderId).to.equal("10248");
      //order.commit();
    });
  }
);

describe('Create work flow',
  async() => {
    it('should create Order OI', async () => {
      let order = new Order();
      order.Order.create();
      order.Order$.created
      expect(order.Order$.created).to.equal(true);
      expect(order.Order$.deleted).to.equal(false);
      order.Order$.ShipCity = "Boston";
      expect(order.Order$.ShipCity).to.equal("Boston");

      order.OrderDetail.create( { UnitPrice: 10.10, Quantity: 1 } )
      expect(order.OrderDetail$.UnitPrice).to.equal(10.10);
      expect(order.OrderDetail$.Quantity).to.equal(1);
      expect(order.Order$.OrderDetail$.Quantity).to.equal(1); // Try full path.
      expect(order.OrderDetail$.Discount).to.be.undefined;
      order.OrderDetail$.Quantity = 2;
      expect(order.OrderDetail$.Quantity).to.equal(2);

      expect( () => order.Product.create() )
        .to.throw( "Entity 'Product' does not have create authority." );

      let product = new Product(loadJson('product1'));

      expect( () => order.Product.include( product.Category$ ) )
        .to.throw( "Entities 'Product' and 'Category' are not the same ER entity." );

      order.Product.include( product.Product$ );
      expect(order.Product$.created).to.equal(false);
      expect(order.Product$.deleted).to.equal(false);
      expect(order.Product$.included).to.equal(true);
      expect(order.Category$.created).to.equal(false);
      expect(order.Category$.deleted).to.equal(false);
      expect(order.Category$.included).to.equal(false);
      expect(order.Category$.updated).to.equal(false);

      expect(order.Product$.ProductId).to.equal("1");
      expect(order.Category$.CategoryId).to.equal("1");
      expect(order.Product$.Category$.CategoryId).to.equal("1");

      expect( () => order.Product$.ProductName = "New Name" )
        .to.throw("Entity 'Product' is not updatable for LOD 'Order'" );
      expect( () => order.Category.create() )
        .to.throw( "Entity 'Category' does not have create authority." );
      expect( () => order.Product.include( product.Product$ ) )
        .to.throw( "Including a new instance for 'Product' voilates max cardinality." );
      expect( () => order.Category.include( product.Product$ ) )
        .to.throw( "Entity 'Category' does not have include authority." );

      expect( () => order.Customer.create() )
        .to.throw( "Entity 'Customer' does not have create authority." );

      let customer = new Customer(loadJson('customer1'));
      order.Customer.include( customer.Customer$ );
      expect(order.Customer$.CompanyName).to.eq("Alfreds Futterkiste");
      expect(order.Customer$.updated).to.eq(false);
      expect( () => order.Customer$.CompanyName = "New Name" )
        .to.throw("Entity 'Customer' is not updatable for LOD 'Order'" );

      customer.Customer$.CompanyName = "New company name";
      expect(order.Customer$.CompanyName).to.eq("New company name");
      expect(customer.Customer$.updated).to.eq(true);
      expect(order.Customer$.updated).to.eq(true);

      let employee = new Employee(loadJson('employee1'));
      order.Employee.include( employee.Employee$ );

      let shipper = new Shipper(loadJson('shipper1'));
      order.Shipper.include( shipper.Shipper$ );
      try {
          let savedOrder = await order.commit();
      } catch (error) {
          if ( error instanceof CommitError ) {
              expect(error.errors.length).to.eq(1);
              expect(error.errors[0].error_code).to.eq("validation");
              expect(error.errors[0].error_message).to.match( /^Required attribute Discount is null.*/);
          }
          else {
            console.log(error);
            throw error;
          }
      }

      order.OrderDetail$.Discount = 0;
      let savedOrder = await order.commit();

      let order2 = await Order.activate( { OrderId: savedOrder.Order$.OrderId } )
      expect(order2.Order$.OrderId).to.eq(savedOrder.Order$.OrderId);
    });
  }
);

const startJettyServer = function() {
    console.log( "Starting jetty server..." );

    const classpathFile = __dirname + '/../../../target/classpath.txt';
    if ( ! fs.existsSync(classpathFile ) ) {
        throw `Classpath file '${classpathFile} does not exist.  Run 'mvn test' to generate it`;
    }

    let jettyPort = 8080;
    let classpath= __dirname + "/../../../target/test-classes:" + fs.readFileSync( classpathFile, 'utf8');
    let args = [ "-ea", "-Xmx100m", "-cp", classpath ];

    if ( debugJettyPort ) {
        args.unshift( "-Xdebug" );
        args.unshift( `-agentlib:jdwp=transport=dt_socket,address=${debugJettyPort},server=${debugJettySuspend},suspend=n` );
    }

    args.push( "com.quinsoft.zeidon.javascript.JettyServer" );
    jettyProcess = child.spawn('java', args );

    jettyProcess.stdout.on('data', (data) => {
        console.log( `stdout: ${data}` );
    });

    jettyProcess.stderr.on('data', (data) => {
        console.log( `stderr: ${data}` );
    });

    return jettyProcess;
}
