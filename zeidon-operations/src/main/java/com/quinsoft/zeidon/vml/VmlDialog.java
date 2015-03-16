/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */

package com.quinsoft.zeidon.vml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.quinsoft.zeidon.TaskQualification;

/**
 * This is a helper class for Dialog Java code generated from VML.  It has some standard
 * "operations" defined so that the generated java can look similar to the generated C.
 *
 * Dialog code that is generated from VML should extend this class.
 *
 * @author DG
 *
 */
public abstract class VmlDialog extends VmlOperation
{
   public VmlDialog( TaskQualification taskQual )
   {
      super( taskQual );
   }

   public static int TransformXslXmlToPDF( TaskQualification taskQual, String xsltFileName, String xmlFileName, String pdfFileName )
   {
      try
      {
         String webDirectory = taskQual.readZeidonConfig( "[App." + taskQual.getApplication().getName( ) + "]", "WebDirectory" );

         taskQual.log().debug( "*** TransformXslXmlToPDF in directory: %s   files: %s  %s  %s", webDirectory, xsltFileName, xmlFileName, pdfFileName );

         FopFactory fopFactory = FopFactory.newInstance( );
         FOUserAgent foUserAgent = fopFactory.newFOUserAgent( );
         foUserAgent.setBaseURL( "file:///" + webDirectory );

         File xsltFile = new File( webDirectory + xsltFileName );
         File xmlFile = new File( webDirectory + xmlFileName );
         File pdfFile = new File( webDirectory + pdfFileName );

         OutputStream outputStream = new java.io.BufferedOutputStream( new java.io.FileOutputStream( pdfFile ) );

         try
         {
            // Create fop with PDF format.
            Fop fop = fopFactory.newFop( MimeConstants.MIME_PDF, foUserAgent, outputStream );

            // Create tranformer for XSLT file.
            TransformerFactory factory = TransformerFactory.newInstance( );
            Transformer transformer = factory.newTransformer( new StreamSource( xsltFile ) );

            // Set the value of a <param> in the stylesheet???
            transformer.setParameter( "versionParam", "2.0" );

            // Set up input for the XSLT transformation
            Source streamSource = new StreamSource( xmlFile );

            // Resulting SAX events (the generated FO) must be piped through to FOP.  Then start
            // XSLT transformation and FOP processing
            transformer.transform( streamSource, new SAXResult( fop.getDefaultHandler( ) ) );
         }
         finally
         {
            outputStream.close( );
         }

         taskQual.log().debug( "*** TransformXslXmlToPDF successfully created: %s%s", webDirectory, pdfFileName );

      }
      catch ( Exception e )
      {
         e.printStackTrace( System.err );
         return -1;
      }

      return 0;
   }

}