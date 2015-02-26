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

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.zeidonoperations;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlOperation;
import com.quinsoft.zeidon.vml.zVIEW;
import com.quinsoft.zeidon.zeidontools.TZRPSXML_Object;

/**
 * This file belongs to the c-to-java conversion system.  These files convert functionality
 * defined in older .c files into Java.  They use non-Java naming conventions to hopefully
 * make it easier to generate the correct .java files using the VML generator.
 *
 * This is the list of Zeidon global operations that are defined in ZDRVROPR.h.
 *
 * @author DG
 *
 */
public class ZDRVROPR extends VmlOperation
{
    private final KZOEP1AA m_KZOEP1AA;
    //private final TZRPSXML_Object m_TZRPSXML;

    public ZDRVROPR( TaskQualification taskQual )
    //public ZDRVROPR( View view )
    {
        super( taskQual );
        m_KZOEP1AA = new KZOEP1AA( taskQual );
        //m_TZRPSXML = new TZRPSXML_Object( view );
    }

    /*
    public ZDRVROPR( Object requiredObject, Object...objects )
    {
        this( findTaskQual( objects ) );
    }
    */

    public int CreateSeeConnection( String SMTPServer, String email, String user, String password )
    {
       // TODO This should be somewhere else.
       // KJS 10/04/12 - I don't think we need to do this anymore.
       // Need to return a positive int. If zero, then the connection was not
       // retrieved.
       return 1;
    }

    public int CloseSeeConnection( int lConnection )
    {
       // TODO This should be somewhere else.
       // KJS 10/04/12 - I don't think we need to do this anymore.
       return 0;
    }

    public int CreateSeeMessage( int lConnection, String szSMTPServer,
                                    String szUserEmailAddress, String szRecipientEmailAddress,
                                    String szCCAddress,String szBCCAddress,
                                    String szSubjectText, int MimeType,
                                    String szMessageBody, String string4, String string5, int attachmentFlag,
                                    String szAttachmentFileName, String szUserEmailName, String szUserEmailPassword )
    {
      InternetAddress fromAddress = null;
      String host = szSMTPServer; //enc-exhub.enc-ad.enc.edu
      String from = szUserEmailAddress;
      String to[] = szRecipientEmailAddress.split("[\\s,;]+");
      InternetAddress[] toAddress = new InternetAddress[to.length];
      String cc[] = szCCAddress.split("[\\s,;]+");
      InternetAddress[] ccAddress = new InternetAddress[cc.length];
      String bcc[] = szBCCAddress.split("[\\s,;]+");
      InternetAddress[] bccAddress = new InternetAddress[bcc.length];
      //String host = "enc-exhub.enc-ad.enc.edu";
      //String from = "kellysautter@comcast.net";
      //String to = "kellysautter@comcast.net";

      // Set properties
      Properties props = new Properties();
      //mail.smtp.sendpartial
      props.put("mail.smtp.host", host);
      props.put("mail.debug", "true");

      // Get session
      // Going to use getDefaultInstance
      // If I get java.lang.SecurityException: Access to default session denied
      // then it said to go use .getInstance(props).
      Session session = Session.getDefaultInstance(props);

      try {
          // Instantiate a message
          Message msg = new MimeMessage(session);

         try
         {
          if ( from != null && !from.isEmpty() )
                fromAddress = new InternetAddress(from);
          if ( szRecipientEmailAddress != null && !szRecipientEmailAddress.isEmpty() )
          {
                for(int iCnt =0; iCnt< to.length; iCnt++)
                {
                   toAddress[iCnt] = new InternetAddress(to[iCnt]);
                }
          }
          if ( szCCAddress != null && !szCCAddress.isEmpty() )
          {
                for(int iCnt =0; iCnt< cc.length; iCnt++)
                {
                   ccAddress[iCnt] = new InternetAddress(cc[iCnt]);
                }
          }
          if ( szBCCAddress != null && !szBCCAddress.isEmpty() )
          {
                for(int iCnt =0; iCnt< bcc.length; iCnt++)
                {
                   bccAddress[iCnt] = new InternetAddress(bcc[iCnt]);
                }
          }
         } catch (AddressException e) {
             task.log().error( "*** CreateSeeMessage: setting addresses **** " );
             task.log().error( e );
             return -1;
         }
         // Set the FROM message
         msg.setFrom(fromAddress);

       if ( szRecipientEmailAddress != null && !szRecipientEmailAddress.isEmpty() )
          msg.setRecipients(Message.RecipientType.TO, toAddress);
         if ( szCCAddress != null && !szCCAddress.isEmpty() )
             msg.setRecipients(Message.RecipientType.CC, ccAddress);
       if ( szBCCAddress != null && !szBCCAddress.isEmpty() )
          msg.setRecipients(Message.RecipientType.BCC, bccAddress);

          // Set the message subject and date we sent it.
          msg.setSubject(szSubjectText);
          msg.setSentDate(new Date());

          // Set message content
          msg.setText(szMessageBody);

          // Send the message
          Transport.send(msg);
      }
      catch(MessagingException mex)
      {
          task.log().error( "*** CreateSeeMessage: Transport.send error ****  " );
          task.log().error( mex );
         // Email was bad?
         if (mex instanceof SendFailedException)
            return -1;

         // SMTP connection bad?
         return -2;
      }

      return 0;
    }

    public int StartEmailClient( String stringEmailAddress,
                                    String stringSubjectLine, String stringCopyToEmailAddress,
                                    String stringBlindCopyEmailAddress, String string, String string2,
                                    String stringEmailClientOverride, int i )
    {
       // TODO This should be somewhere else.
       return 0;
    }


    @Override
    public int PrintTextBlob( View view, String entityName, View printView,
                                 String string2, String string3, String string4, int i )
    {
       // TODO Auto-generated method stub
       return 0;
    }

   public void RefreshCtrl( View ViewToWindow, String ctrlName )
   {
      // TODO - Do we need to create code here?
   }

   public int PrintReportToPDF( View ViewToWindow, View view, String entityName,
                                String reportDefName, String reportName, int flag)
   {
     //String pdfName="";
     String xmlName="";
     String xslName="";
     //StringBuilder xsltPath;
     //StringBuilder pdfPath;

     // We need to create the xml file.  Then call ConvertXMLToPDF which uses the XSLT file created
     // from within the report to create a pdf file.
     //GenerateXSLT_XML( view, reportDefName, reportName, entityName );
     //m_TZRPSXML.oTZRPSRCO_GenerateXSLT_XML( view, reportDefName, reportName, entityName );
     new TZRPSXML_Object( ViewToWindow ).oTZRPSRCO_GenerateXSLT_XML( view, reportDefName, reportName, entityName );
      //pdfPath = new StringBuilder( );
     String xsltPath = task.readZeidonConfig( task.getApplication().getName(), "XSLTDirectory" );
     //m_KZOEP1AA.SysReadZeidonIni( -1, "[App.Zencas]", "XSLTDirectory", xsltPath );

     //pdfName = pdfPath.toString() + "pdf/" + reportName + ".pdf";
     xmlName = xsltPath + reportName + ".xml";
     xslName = xsltPath + reportDefName + ".xsl";

     ConvertXMLToPDF( xmlName, xslName, reportName );

     return 0;
   }

   public int ConvertXMLToPDF( String xml, String xsl, String pdf )
   {
      // Examples can be found...
      //http://xmlgraphics.apache.org/fop/1.0/embedding.html#examples
      pdf = pdf + ".pdf";

      StringBuilder sb_szPathName;
      sb_szPathName = new StringBuilder( 200 );
   // m_KZOEP1AA.SysReadZeidonIni( -1, "[App.Zencas]", "WebDirectory", sb_szPathName );
   // String szPathName = task.readZeidonConfig( task.getApplication().getName(), "WebDirectory" );
      String szPathName = task.readZeidonConfig( "[App." + task.getApplication().getName() + "]", "WebDirectory" );
      szPathName = m_KZOEP1AA.SysConvertEnvironmentString( "", szPathName );

      //String copyPdf = "C:/Program Files/Apache Group/Tomcat 7.0/webapps/ROOT/epamms/pdf/" + pdf;
      String copyPdf = szPathName + "pdf/" + pdf;
      task.log().error( "*** ConvertXMLToPDF file: " + copyPdf );

      try
      {
         // Step 1: Construct a FopFactory
         // (reuse if you plan to render multiple documents!)
         FopFactory fopFactory = FopFactory.newInstance();

         // Step 2: Set up output stream.
         // Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
         //OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:/temp/name.pdf")));
         OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(copyPdf)));
         try
         {
            // Step 3: Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Step 4: Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            //Transformer transformer = factory.newTransformer(); // identity transformer

            //without XSLT:
            //Transformer transformer = factory.newTransformer(); // identity transformer

            //with XSLT:
            //javax.xml.transform.Source xslt = new StreamSource(new File("c:/temp/name2fo.xsl"));
            javax.xml.transform.Source xslt = new StreamSource(new File(xsl));
            Transformer transformer = factory.newTransformer(xslt);

            // Step 5: Setup input and output for XSLT transformation
            // Setup input stream
            //Source src = new StreamSource(new File("C:/Temp/myfile.fo"));
            //javax.xml.transform.Source src = new StreamSource(new File("C:/temp/name.xml"));
            javax.xml.transform.Source src = new StreamSource(new File(xml));
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Step 6: Start XSLT transformation and FOP processing
            transformer.transform(src, res);

            // We set the report name in KZXMLPGO so that
            // we can retrieve this name in FindOpenFile (kzoejava.c) when trying to
            // open the file in the jsp files.
            View vKZXMLPGO = task.getViewByName( "_KZXMLPGO" );
            vKZXMLPGO.cursor("Session").getAttribute( "PrintFileName" ).setValue( pdf );
            vKZXMLPGO.cursor("Session").getAttribute( "PrintFileType" ).setValue( pdf );
         }
         catch (Exception e)
         {
            task.log().error( "*** ConvertXMLToPDF transform exception **** " + e );
         } finally {
            //Clean-up
            out.close();
         }
      }
      catch (Exception e)
      {
         task.log().error( "*** ConvertXMLToPDF factory exception **** " + e );
      }

      return 0;
   }

   public int ConvertXML_ToPDF( String directory, String application, String label )
   {
      // Examples can be found...
      //http://xmlgraphics.apache.org/fop/1.0/embedding.html#examples
      String pdf = label + ".pdf";
      String root = directory + application;
      String xml = root + "xml/";
      String xsl = root + "xsl/";
      String copyPdf = root + "pdf/";
      SysValidDirOrFile( xml, 1, 1, 256 );
      SysValidDirOrFile( xsl, 1, 1, 256 );
      SysValidDirOrFile( copyPdf, 1, 1, 256 );
      xml += label + ".xml";
      xsl += label + ".xsl";
      copyPdf += pdf;
      task.log().error( "*** ConvertXML_ToPDF xml file: " + xml + "   xsl file: " + xsl + "   pdf file: " + copyPdf );

      try
      {
         // Step 1: Construct a FopFactory
         // (reuse if you plan to render multiple documents!)
         FopFactory fopFactory = FopFactory.newInstance();

         // Step 2: Set up output stream.
         // Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
         //OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:/temp/name.pdf")));
         OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(copyPdf)));
         try
         {
            // Step 3: Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Step 4: Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            //Transformer transformer = factory.newTransformer(); // identity transformer

            //without XSLT:
            //Transformer transformer = factory.newTransformer(); // identity transformer

            //with XSLT:
            //javax.xml.transform.Source xslt = new StreamSource(new File("c:/temp/name2fo.xsl"));
            javax.xml.transform.Source xslt = new StreamSource(new File(xsl));
            Transformer transformer = factory.newTransformer(xslt);

            // Step 5: Setup input and output for XSLT transformation
            // Setup input stream
            //Source src = new StreamSource(new File("C:/Temp/myfile.fo"));
            //javax.xml.transform.Source src = new StreamSource(new File("C:/temp/name.xml"));
            javax.xml.transform.Source src = new StreamSource(new File(xml));
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Step 6: Start XSLT transformation and FOP processing
            transformer.transform(src, res);

            // We set the report name in KZXMLPGO so that
            // we can retrieve this name in FindOpenFile (kzoejava.c) when trying to
            // open the file in the jsp files.
            View vKZXMLPGO = task.getViewByName( "_KZXMLPGO" );
            vKZXMLPGO.cursor("Session").getAttribute( "PrintFileName" ).setValue( application + "pdf/" + pdf );
            vKZXMLPGO.cursor("Session").getAttribute( "PrintFileType" ).setValue( application + "pdf/" + pdf );
         }
         catch (Exception e)
         {
            task.log().error( "*** ConvertXML_ToPDF transform exception **** " + e );
         } finally {
            out.close();  //Clean-up
         }
      }
      catch (Exception e)
      {
         task.log().error( "*** ConvertXML_ToPDF factory exception **** " + e );
      }

      return 0;
   }

   public int PrintReportToHTML( View viewToWindow, View resultSetSingle,
                                 String rootEntityName, String reportName, String reportFullName, int i )
   {
      // TODO ... DKS???
      return 0;
   }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // ImportCSV_ToZeidonOI
    //
    //    TgtView             target view
    //    CSV FileName        file name of CSV data
    //
    // The first line of the CSV file is expected to have entity.attribute
    // pairs (separated by commas) for each attribute in the CSV.  The first
    // line is followed by comma-separated attribute lines.
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int ImportCSV_ToZeidonOI( View   vTgt,
                                     String cpcCSV_FileName ) throws IOException
    {
       Stack<ZNameItem> EntityStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> AttributeStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> ValueStack = new Stack<ZNameItem>( );   // tag stack (need not be unique)
       Stack<ZNameItem> ContextStack = new Stack<ZNameItem>( ); // tag stack (need not be unique)
       ZNameItem pEntityItem;
       ZNameItem pAttribItem;
       ZNameItem pValueItem;
       ZNameItem pContextItem;
       StringBuilder sbLine = new StringBuilder( );
       StringBuilder sbAttributeValue = new StringBuilder( );
       String pchNext;
       int    hFileCSV;
       int    lLineNbr;
       int    k;
       int    nRC;

       KZOEP1AA m_KZOEP1AA = new KZOEP1AA( vTgt );
       hFileCSV = m_KZOEP1AA.SysOpenFile( vTgt, cpcCSV_FileName, COREFILE_READ );
       if ( hFileCSV == -1 )
          return( -1 );

       nRC = m_KZOEP1AA.SysReadLine( vTgt, sbLine, hFileCSV );
       if ( nRC != 1 )
          return( -2 );  // entity.attribute header line not read successfully

       // Even though we don't use the Context here, set up for it.
       fnSetEntityAttribList( EntityStack, AttributeStack, ValueStack, ContextStack, "", sbLine.toString( ) );
       if ( EntityStack.isEmpty( ) || AttributeStack.isEmpty( ) )
          return( -3 );  // entity.attribute header line not well-formed

       lLineNbr = 1;     // account for entity.attribute header line
       nRC = m_KZOEP1AA.SysReadLine( vTgt, sbLine, hFileCSV );
       while ( nRC == 1 )
       {
          lLineNbr++;
          if ( sbLine != null && sbLine.length( ) != 0 )
          {
             pchNext = sbLine.toString( );
             for ( k = 0; k < AttributeStack.size( ); k++ )
             {
                pEntityItem = EntityStack.get( k );
                pAttribItem = AttributeStack.get( k );
                pValueItem = ValueStack.get( k );
                pContextItem = ContextStack.get( k );
                if ( k == 0 )
                   CreateEntity( vTgt, pEntityItem.getName( ), zPOS_LAST );
                else
                if ( CheckExistenceOfEntity( vTgt, pEntityItem.getName( ) ) != zCURSOR_SET )
                   CreateEntity( vTgt, pEntityItem.getName( ), zPOS_LAST );

                pchNext = fnGetNextAttribute( pchNext, sbAttributeValue, lLineNbr );
                SetAttributeFromString( vTgt, pEntityItem.getName( ), pAttribItem.getName( ), sbAttributeValue.toString( ) );
                if ( pchNext == null || pchNext.isEmpty( ) )
                   break;
             }
          }

          nRC = m_KZOEP1AA.SysReadLine( vTgt, sbLine, hFileCSV );
       }

       m_KZOEP1AA.SysCloseFile( vTgt, hFileCSV, 0 );
       return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BuildCSV_FromEntityAttribute
    //
    //    TgtView             target view
    //    TgtEntity           target entity
    //    TgtAttribute        target attribute (text or blob)
    //    SrcView             source view
    //    SrcEAC              source entity.attribute.context (list)
    //    lFlag               0-<crlf>  1-<p></p>
    //
    // The SrcEAC parameter contains entity.attribute.context triples (separated
    // by commas if more than one) for each attribute to be set in the CSV.
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int BuildCSV_FromEntityAttribute( View    vTgt,
                                             String  cpcTgtEntity,
                                             String  cpcTgtAttribute,
                                             View    vSrc,
                                             String  cpcSrcListEntityScope,
                                             String  cpcSrcEAC,
                                             int     lFlag )
    {
       Stack<ZNameItem> EntityStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> AttributeStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> ValueStack = new Stack<ZNameItem>( );   // tag stack (need not be unique)
       Stack<ZNameItem> ContextStack = new Stack<ZNameItem>( ); // tag stack (need not be unique)
       ZNameItem pEntityItem;
       ZNameItem pAttribItem;
       ZNameItem pValueItem;
       ZNameItem pContextItem;
       String pchSrcListEntity;
       String pchSrcListScope;
       StringBuilder sb;
       String pch = null;
       int    ulCurrLth;
       int    ulMaxLth = 0;
       int    k;
       int    nRC;

       k = zstrchr( cpcSrcListEntityScope, '.' );
       if ( k > 0 )
       {
          pchSrcListEntity = cpcSrcListEntityScope.substring( 0, k );
          pchSrcListScope = cpcSrcListEntityScope.substring( k + 1 );
       }
       else
       {
          pchSrcListEntity = cpcSrcListEntityScope;
          pchSrcListScope = null;
       }

       fnSetEntityAttribList( EntityStack, AttributeStack, ValueStack, ContextStack, pchSrcListEntity, cpcSrcEAC );

       if ( EntityStack.size( ) == 0 || AttributeStack.size( ) == 0 )
          return( -3 );  // entity.attribute.context parameter not well-formed

       MutableInt nLth = new MutableInt( 0 );
       GetAttributeLength( nLth, vTgt, cpcTgtEntity, cpcTgtAttribute );
       ulMaxLth = nLth.intValue( );
       sb = new StringBuilder( ulMaxLth + 16 );  // extra room for adding <crlf> or <p></p>
       sb.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.

    // DisplayObjectInstance( vSrc, "", "" );
       ulCurrLth = 0;
       nRC = SetCursorFirstEntity( vSrc, pchSrcListEntity, pchSrcListScope );
       while ( nRC >= zCURSOR_SET )
       {
          k = 0;
          if ( k < AttributeStack.size( ) )
          {
             pEntityItem = EntityStack.get( k );
             pAttribItem = AttributeStack.get( k );
             pValueItem = ValueStack.get( k );
             pContextItem = ContextStack.get( k );
          }
          else
          {
             pEntityItem = null;
             pAttribItem = null;
             pValueItem = null;
             pContextItem = null;
          }

          while ( pAttribItem != null )
          {
             if ( (lFlag & 0x00000001) != 0 )
             {
                sb.insert( ulCurrLth++, '<' );
                sb.insert( ulCurrLth++, 'p' );
                sb.insert( ulCurrLth++, '>' );
             }

             pch = GetStringFromAttributeByContext( pch, vSrc,
                                                    pEntityItem.getName( ),
                                                    pAttribItem.getName( ),
                                                    pContextItem.getName( ),
                                                    ulMaxLth - ulCurrLth );
             ulCurrLth = zstrcpy( sb, ulCurrLth, pch );
             k++;
             if ( k < AttributeStack.size( ) )
             {
                pEntityItem = EntityStack.get( k );
                pAttribItem = AttributeStack.get( k );
                pValueItem = ValueStack.get( k );
                pContextItem = ContextStack.get( k );
             }
             else
             {
                pEntityItem = null;
                pAttribItem = null;
                pValueItem = null;
                pContextItem = null;
             }

             if ( pAttribItem != null )
             {
                sb.insert( ulCurrLth++, ',' );
                sb.insert( ulCurrLth++, ' ' );
             }
             else
             {
                if ( (lFlag & 0x00000001) != 0 )
                {
                   sb.insert( ulCurrLth++, '<' );
                   sb.insert( ulCurrLth++, '/' );
                   sb.insert( ulCurrLth++, 'p' );
                   sb.insert( ulCurrLth++, '>' );
                }
                else
                {
                   sb.insert( ulCurrLth++, '\r' );
                   sb.insert( ulCurrLth++, '\n' );
                }
             }

             if ( ulCurrLth >= ulMaxLth )
             {
                break;
             }
          }

          if ( ulCurrLth >= ulMaxLth )
             nRC = zCALL_ERROR;
          else
             nRC = SetCursorNextEntity( vSrc, pchSrcListEntity, pchSrcListScope );
       }

       SetAttributeFromString( vTgt, cpcTgtEntity, cpcTgtAttribute, sb.toString( ) );
       return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BuildEntityAttributeFromCSV
    //
    //    SrcView             source view
    //    SrcEntity           source entity
    //    SrcAttribute        source attribute (text or blob)
    //    TgtView             target view
    //    TgtEAC              target entity.attribute=value.context (list)
    //    lFlag               0-<crlf>  1-<p></p>
    //
    // The TgtEAC parameter contains entity.attribute=value.context triples (separated
    // by commas if more than one) for each attribute to be set from the CSV.  If a
    // value is specified, it is a constant value for the given attribute.
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int BuildEntityAttributeFromCSV( View    vSrc,
                                            String  cpcSrcEntity,
                                            String  cpcSrcAttribute,
                                            View    vTgt,
                                            String  cpcTgtListEntity,
                                            String  cpcTgtEAC,
                                            int     lFlag )
    {
       StringBuilder sbAttributeValue = new StringBuilder( );
       Stack<ZNameItem> EntityStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> AttributeStack = new Stack<ZNameItem>( );  // tag stack (need not be unique)
       Stack<ZNameItem> ValueStack = new Stack<ZNameItem>( );   // tag stack (need not be unique)
       Stack<ZNameItem> ContextStack = new Stack<ZNameItem>( ); // tag stack (need not be unique)
       ZNameItem pEntityItem;
       ZNameItem pAttribItem;
       ZNameItem pValueItem;
       ZNameItem pContextItem;
       String pch = null;
       String pchNext = null;
       StringBuilder sbLine = new StringBuilder( );
       StringBuilder sbNewline = new StringBuilder( );
       int    nNewline;
       int    ulCurrLth;
       int    ulMaxLth;
       int    lLineNbr;
       int    lPos;
       int    k;
       boolean bCreated;

       fnSetEntityAttribList( EntityStack, AttributeStack, ValueStack, ContextStack, cpcTgtListEntity, cpcTgtEAC );

       if ( EntityStack.size( ) == 0 || AttributeStack.size( ) == 0 )
          return -3;  // entity.attribute.context parameter not well-formed

       pch = GetStringFromAttribute( pch, vSrc, cpcSrcEntity, cpcSrcAttribute );
       ulMaxLth = zstrlen( pch );  // get true length

       lLineNbr = 0;
       ulCurrLth = 0;
       zstrcpy( sbLine, pch );
       while ( ulCurrLth < ulMaxLth )
       {
          bCreated = false;
          lLineNbr++;
          if ( (lFlag & 0x00000001) != 0 )
          {
             nNewline = zstrstr( sbLine, ulCurrLth, "</p>" );
             lPos = ulCurrLth + 4;
          }
          else
          {
             nNewline = zstrstr( sbLine, ulCurrLth, "\r\n" );
             lPos = ulCurrLth;
          }

          if ( nNewline >= 0 )
          {
             pchNext = sbLine.substring( lPos, nNewline );
             ulCurrLth = nNewline;
          }
          else
          {
             pchNext = sbLine.substring( lPos );
             ulCurrLth = ulMaxLth;
          }

          // Run through the EA list.
          for ( k = 0; k < AttributeStack.size( ) && (pAttribItem = AttributeStack.get( k )) != null; k++ )
          {
             pEntityItem = EntityStack.get( k );
             pValueItem = ValueStack.get( k );
             pContextItem = ContextStack.get( k );

             if ( pValueItem.getFlag( ) != 0 )
             {
                if ( bCreated = false && pValueItem.getName( ).isEmpty( ) == false )
                {
                   bCreated = true;
                   CreateEntity( vTgt, pEntityItem.getName( ), zPOS_LAST );
                }

                if ( pValueItem.getName( ).isEmpty( ) == false )
                   SetAttributeFromString( vTgt, pEntityItem.getName( ), pAttribItem.getName( ), pValueItem.getName( ) );
             }
             else
             {
                pchNext = fnGetNextAttribute( pchNext, sbAttributeValue, lLineNbr );
                if ( bCreated == false && sbAttributeValue.length( ) > 0 )
                {
                   bCreated = true;
                   CreateEntity( vTgt, pEntityItem.getName( ), zPOS_LAST );
                }

                if ( sbAttributeValue.length( ) > 0 )
                   SetAttributeFromString( vTgt, pEntityItem.getName( ), pAttribItem.getName( ), sbAttributeValue.toString( ) );
             }
          }

          if ( nNewline >= 0 )
          {
             if ( (lFlag & 0x00000001) != 0 )
             {
                ulCurrLth +=4;
             }
             else
             {
                ulCurrLth += 2;
             }
          }
       }

       return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // AddHTML_TagAttributes
    //    If the tag does not already exist in the HTML text, add the tag as
    //    the "outermost" tag.  Add/replace attributes to the tag.
    //
    //    vTaskView              Task View
    //    pchHTML_Text           HTML text
    //    lMaxLth                Maximum length of HTML text
    //    cpcHTML_Tag            HTML tag
    //    cpcHTML_AttributeName  Attribute name to set for tag
    //    cpcHTML_AttributeValue Value of attribute
    //    cpcDelimiter           Delimiter to wrap attribute
    //    lFlags                 0 - replace value; 1 - append value
    //
    // Returns:  -1 - error
    //          otw - number of tags found at level (does not include the one added).
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int
    AddHTML_TagAttribute( View    vTaskView,
                          String  pchHTML_Text,
                          int     lMaxLth,
                          String  cpcHTML_Tag,
                          String  cpcHTML_AttributeName,
                          String  cpcHTML_AttributeValue,
                          String  cpcDelimiter,
                          int     lFlags )
    {
       String  csPage = pchHTML_Text;
       String  csTag = cpcHTML_Tag;
       ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );
       int     lth;
       int    nRC;

       TraceLine( "AddHTML_TagAttribute Tag: %s   Attribute Name: %s   Attribute Value: %s   Length: %d   HTML: %s",
                  cpcHTML_Tag, cpcHTML_AttributeName, cpcHTML_AttributeValue, csPage.length( ), csPage );
       pParseHTML.ParseHTML_Page( csPage, "" );
       nRC = pParseHTML.AddTagAndAttributeToList( csTag, cpcHTML_AttributeName, cpcHTML_AttributeValue,
                                                  cpcDelimiter.length( ) > 0 ? cpcDelimiter.charAt( 0 ) : 0, lFlags );

       pParseHTML.ReconstituteHTML( csPage, "", 0 ); // Flags 0 ==> full HTML

       // done with pParseHTML

       lth = csPage.length( );
       if ( lth > lMaxLth )
       {
          TraceLine( "AddHTML_TagAttribute length: %d  exceeded maximum: %d", lth, lMaxLth );
          TraceLineS( "AddHTML_TagAttribute original: ", pchHTML_Text );
          TraceLineS( "AddHTML_TagAttribute reformat: ", csPage );
          nRC = -1;
       }
       else
       {
          // Things are good.  Send back the revised HTML.
          zstrcpy( pchHTML_Text, csPage );
       }

       return nRC;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RemoveTagFromHTML
    //    Remove the tag from the HTML text, and return it.
    //
    //    vTaskView              Task View
    //    pchReturnTag           Return tag
    //    pchHTML_Text           HTML text
    //    cpcTag                 HTML tag to return
    //
    // Returns: 0
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int
    RemoveTagFromHTML( View    vTaskView,
                       StringBuilder sbReturnTag,
                       StringBuilder sbHTML_Text,
                       String  cpcTag )
    {
       String  csPage = sbHTML_Text.toString( );
       String  csTag = cpcTag;
       ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );

       TraceLine( "RemoveTagFromHTML Tag: %s   HTML: %s", cpcTag, csPage );
       pParseHTML.ParseHTML_Page( csPage, "" );
       String csReturnTag = pParseHTML.RemoveTag( csPage, csTag );

       // done with pParseHTML

       // Things are good.  Send back the revised HTML.
       zstrcpy( sbReturnTag, csReturnTag );
       zstrcpy( sbHTML_Text, csPage );

       return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // The FindLinks Test Operation
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public void
    FindLinks( View vApp, String  cpcEntity, String  cpcAttribute )
    {
       String  pchHTML = null;
       String  csPage;

       pchHTML = GetAddrForAttribute( pchHTML, vApp, cpcEntity, cpcAttribute );
       csPage = pchHTML;
       if ( StringUtils.isBlank( csPage ) )
       {
          TraceLine( "FindLinks HTML is empty for Entity.Attribute: %s.%s",
                     cpcEntity, cpcAttribute );
       }
       else
       {
          ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );

          TraceLine( "FindLinks HTML looking for 'src' in HTML from Entity.Attribute: %s.%s",
                     cpcEntity, cpcAttribute );
          pParseHTML.ParseHTML_Page( csPage, "src" );
          pParseHTML.ReconstituteHTML( csPage, "", 0 );
          TraceLineS( "FindLinks HTML: ", csPage );
          // done with pParseHTML
       }
    }
/*
    public int PDFDocumentCreate( String fileName ) throws IOException
    {
       // Create a new document.
       Document document = new Document();

       try
       {
          // Get an instance of PdfWriter and create a HelloWorld.pdf file as an output.
          PdfWriter.getInstance(document, new FileOutputStream(new File("HelloWorld.pdf")));
          document.open();

          // Create our first paragraph for the pdf document to be created. We also set
          // the alignment and the font of the paragraph.
          String text = "Kode Java website provides beginners to Java programming some examples " +
                        "to use the Java API (Application Programming Interface) to develop applications. " +
                        "Learning from some examples will hopefully decrease the time required to learn Java.";
          Paragraph paragraph = new Paragraph(text);
          paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
          BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
          paragraph.setFont(new Font(helvetica, 10, Font.NORMAL));

          document.add(paragraph);
       }
       catch (DocumentException e)
       {
          e.printStackTrace();
       }
       catch (FileNotFoundException e)
       {
          e.printStackTrace();
       }
       finally
       {
          document.close();
       }

       return 0;
    }
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  THESE SHOULD BE IN VmlDialog.java ... BUT CURRENTLY CANNOT RESOLVE THEM FROM THERE ... SO THEY ARE HERE!!!
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // To turn on tracing for palette messages, you need to define _TRACEPAL
    // here or in your make file
    //
    // #define _TRACEPAL
    // Parsing HTML in Microsoft C#
    // By Jeff Heaton
    //
    // Most data on the Web is stored in the Hypertext Markup Language (HTML) format.
    // There are many times that you might want to parse HTML in your C# application.
    // However, the .NET framework does not provide an easy way to parse HTML.
    // Evidence of this is the numerous questions posted by C# programmers looking
    // for an easy way to parse HTML.
    //
    // The Microsoft .NET framework includes extensive support for Extensible Markup
    // Language (XML). However, although XML and HTML look very similar, they are not
    // very compatible. Consider the following major differences between XML and HTML:
    //
    // XML requires end tags.
    // All XML attribute values must be fully quoted with either single or double quotes.
    // XML tags must be properly nested.
    // XML tag names are case sensitive.
    // XML does not allow duplicate attributes.
    // Empty attributes are not allowed in XML.
    //
    // Let's look at one of these examples in code to illustrate the difference. In
    // XML, every beginning tag must have an ending tag. The following HTML would cause
    // problems for an XML parser.
    //
    // <p>This is line 1<br>
    // This is line 2</p>
    //
    // This is just one of many differences. Of course, you can require HTML to be
    // written in such a way that it is compatible with XML. The preceding HTML
    // could be rewritten as in the following example.
    //
    // <p>This is line 1<br/>
    // This is line 2</p>
    //
    // Both an XML parser and any modern browser could understand this. Unfortunately,
    // this is not a viable solution because you do not control the sources of HTML.
    // You will want your program to be able to process HTML from any source.
    //
    // The Solution
    // Because of this, I found it necessary to write my own HTML parser. In this
    // article, I will show you how my HTML parser was constructed, and how you can
    // use this parser with your own applications. I will begin by showing you the
    // main components that make up the HTML parser. I will conclude this article
    // by showing a simple example that uses the HTML parser.
    //
    // The HTML parser consists of the following four classes:
    //
    // ZHTML_Attribute - The attribute class is used to hold an individual attribute
    // inside an HTML tag.
    // ZHTML_TagAttributeList - The attribute list holds an individual HTML tag and all
    // of its attributes.
    // ZHTML_Parse - Holds general text parsing routines.
    // ZHTML_ParseHTML - The main class that you will interface with; the ZHTML_ParseHTML
    // class is fed the HTML that you would like to parse.
    //
    // I will now show you how each of these classes functions, and how you will
    // use them. I will begin with the ZHTML_Attribute class.
    //
    // The ZHTML_Attribute Class
    // The ZHTML_Attribute class is used to store individual HTML attributes. The source
    // code for the ZHTML_Attribute class can be seen in Listing 1. The following HTML
    // tag demonstrates attributes:
    //
    // <img src="picture.gif" alt="Some Picture">
    // The above HTML tag has two attributes named "src" and "alt". The values
    // of these two attributes are "picture.gif" and "Some Picture", respectively.
    //
    // The ZHTML_Attribute class consists of three properties named "getName( )", "m_csValue",
    // and "m_chDelimiter". The "getName( )" property stores the name of the attribute. The
    // "m_csValue" property stores the value held by the property. And finally, the
    // "m_chDelimiter" property holds the character that was used to delimit the value,
    // if any. This property will either hold a quote ("), an apostrophe ('), or
    // nothing at all, depending on what was used to delineate the value.
    //
    // The ZHTML_TagAttributeList Class
    // An HTML tag often consists of several attributes. The "ZHTML_TagAttributeList"
    // class is used to hold a list of these attributes. The "ZHTML_TagAttributeList"
    // class is shown in Listing 2. The "ZHTML_TagAttributeList" class consists of a
    // name and a collection of attributes. The "ZHTML_TagAttributeList" name, stored
    // in a property called "name", holds the name of the tag. When tags are
    // returned to you from the parser, they will be in the form of "ZHTML_TagAttributeList"
    // objects.
    //
    // The ZHTML_TagAttributeList class makes use of the C# indices. You can access
    // individual attributes both by numeric and string indicies. For example,
    // if an attribute "src" were stored in the "ZHTML_TagAttributeList" object "theTag",
    // you could access the "src" attribute in the following ways:
    //
    // theTag[ 0 ]    // assuming "src" was the first attribute
    // theTag[ "src" ]
    //
    // Both of these methods could be used to access the attributes of the tag.
    //
    // The ZHTML_Parse and ZHTML_ParseHTML Classes
    // If you only want to use the classes to parse HTML, you need not be concerned with
    // the "ZHTML_Parse" class. The "ZHTML_Parse" class is used internally by the HTML
    // parser to provide low-level support for attribute-value based files, such as
    // HTML, SGML, XML, or even HTTP headers. The source code for the "ZHTML_Parse" class
    // is shown in Listing 3. I will not cover the "ZHTML_Parse" class at length in this
    // article. If you are interested in the ZHTML_Parse class, all of its methods are
    // commented.
    //
    // The "ZHTML_ParseHTML" class subclasses the "ZHTML_Parse" class. The "ZHTML_ParseHTML"
    // class provides the HTML-specific code needed to make the parser work with HTML.
    // The ZHTML_ParseHTML class is shown in Listing 4. The "ZHTML_ParseHTML" class will
    // be your primary interface to the HTML parser. Actually, using the HTML parser
    // is covered in the next section. The methods that you will use primarily are
    // listed here.
    //
    //          public char ZHTML_Parse( )
    //          public ZHTML_TagAttributeList GetTag( )
    //
    // The ZHTML_Parse() method is called to retrieve the next character in the HTML
    // file that you are parsing. If the next character is part of a tag, the value of
    // zero is returned. When you see that ZHTML_Parse( ) has returned zero, you know
    // that you must process an HTML tag. You can access the tag by calling the GetTag
    // method. The GetTag method will return an ArrayList object that contains the tag
    // and all of its attributes.
    //
    // Using the HTML Parser
    // Now, I will show you an example of how to use the HTML parser. The example
    // program will prompt you for a URL and then show you all of the hyperlinks
    // contained within the HTML file located at the URL you specified. This example
    // will only work on URLs that lead to HTML data; it will not work with images
    // or other binary data. This example is shown in Listing 5.
    //
    // To see the program work enter any URL address, such as http://www.developer.com.
    // The program will then display all of the hyperlinks contained on the homepage
    // of Developer.COM.
    //
    // The loop that processes the page is shown here.
    //
    // ZHTML_ParseHTML parse = new ZHTML_ParseHTML( );
    // parse.Source = page;
    // while ( !parse.Eof( ) )
    // {
    //        char ch = parse.ZHTML_Parse( );
    //        if ( ch == 0 )
    //        {
    //          ZHTML_TagAttributeList tag = parse.GetTag( );
    //          if ( tag[ "href" ] != null )
    //            System.Console.WriteLine( "Found link: " + tag[ "href" ].Value );
    //        }
    // }
    //
    // As you can see, a ZHTML_ParseHTML object is instantiated, and the object's Source
    // property is set to the HTML page to be parsed. A loop continues so long as
    // the end of the pages has not been reached. We ignore regular characters and
    // look for each tag, noted by when ch is equal to zero. For each tag, we check
    // to see if it has an HREF attribute. If an HREF attribute is present, the
    // link is displayed.
    //
    // Conclusions
    // As you can see, these classes provide an easy-to-use framework for HTML
    // parsing that you can reuse in any Microsoft .NET application. The example
    // program provided uses the parser only to display links. However, this parser
    // has been used in a variety of complex HTML parsing applications.
    //
    // This source code may be used freely under the Limited GNU Public License (LGPL).
    // Written by Jeff Heaton (http://www.jeffheaton.com)
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    static final String g_csWhiteSpace = "\t\n\r ";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // The Attribute Class
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private static class ZHTML_Attribute
    {
    // public  int     m_t;           // debugging only

       private String  m_csName;      // name of this attribute
       private String  m_csValue;     // value of this attribute
       private char    m_chDelimiter; // delimiter for the value of this attribute (e.g. " or ')


       // ZHTML_Attribute holds one attribute, as is normally stored in an
       // HTML or XML file. This includes a name, value and delimiter.

       // ctor
       private ZHTML_Attribute( )
       {
          m_csName = "";
          m_csValue = "";
          m_chDelimiter = (char) 0;
       // m_t = 0;  // debugging only
       }

       // Construct a new ZHTML_Attribute.  The name, delimiter, and value properties can be specified here.
       // Parameters:
       //   csName - name of this attribute.
       //   csValue - value of this attribute.
       //   chDelimiter - delimiter character for the value.
       private ZHTML_Attribute( String csName, String csValue, char chDelimiter )
       {
          m_csName = csName.toLowerCase( );
          m_csValue = csValue;
          m_chDelimiter = chDelimiter;
       }

       // Getter/Setter for delimiter of this attribute.
       public char getDelimiter( ) { return( m_chDelimiter ); }
       public void setDelimiter( char chDelimiter ) { m_chDelimiter = chDelimiter; }

       // Getter/Setter for name of this attribute.
       public String  getName( ) { return( m_csName ); }
       public void setName( String csName ) { m_csName = csName; }

       // Getter/Setter for value of this attribute.
       public String  getValue( ) { return( m_csValue ); }
       public void setValue( String csValue ) { m_csValue = csValue; }

       @Override
    protected void finalize( ) throws Throwable
       {
       // TraceLineI( "ZHTML_Attribute::dtor for: ", m_t );
       }

    } // end of: private class ZHTML_Attribute


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // The TagAttributeList Class
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    protected static final int zMIXSET_DUP = -1;

    private class ZHTML_TagAttributeList
    {
       private ArrayList<ZHTML_Attribute> m_list = null;
       private String  m_csTag;       // tag to which attributes apply
       private boolean m_bTag;
       private int     m_nTagType;    // 1 - open tag; 2 - close tag; 3 - open/close tag
       private int     m_nErrorCode;

       // Getter/Setter for tag of this tag attribute.
       public String  GetTag( ) { return( m_csTag ); }
       public void    SetTag( String csTag ) { m_csTag = csTag; }

       // Getter/Setter for tag type of this tag attribute.
       public int  GetTagType( ) { return( m_nTagType ); }
       public void SetTagType( int nTagType ) { m_nTagType = nTagType; }


       // Create a new, empty, attribute list.
       private ZHTML_TagAttributeList( )
       {
          m_list = new ArrayList<ZHTML_Attribute>( );
          ClearInit( );
       }

       // Delete attribute list.
       @Override
    protected void finalize( ) throws Throwable
       {
          ClearInit( );
       }

       /***#if 0
       // #region ICloneable Members
       // public virtual object Clone( )
       // {
       //    return( new ZHTML_Attribute( getName( ), m_csValue, m_chDelimiter ) );
       // }
       // #endregion

       // Make an exact copy of this object.
       // Returns a new object that is a clone of the specified object.
       Clone( )
       {
          ZHTML_TagAttributeList rtn = new ZHTML_TagAttributeList( );

          for ( int   k = 0; k < m_list.Count; k++ )
            rtn.Add( (ZHTML_Attribute) this[ k ].Clone( ) );

          return( rtn );
       }
       #endif***/

       // Add the specified attribute to the list of attributes.
       // Parm: pAttribute - Attribute to add to this ZHTML_TagAttributeList.
       private int
       AddAttributeToList( ZHTML_Attribute pAttribute )
       {
          if ( GetItem( pAttribute.getName( ) ) != null )
             return( m_nErrorCode = zMIXSET_DUP );

          m_list.add( pAttribute );
          return 0;
       }

       // Clear all attributes from this ZHTML_TagAttributeList and return it to an empty state.
       private void
       ClearInit( )
       {
          if ( m_list != null )
             m_list.clear( );  // ResetContent( );

          m_csTag = "";     // Empty( );
          m_bTag = false;
          m_nTagType = 0;
          m_nErrorCode = 0;
       }

       // Returns true if this ZHTML_TagAttributeList is empty, with no attributes ... false otherwise.
       private boolean
       IsListEmpty( )
       {
          return( m_list.isEmpty( ) == true );
       }

       // If there is already an attribute with the specified name, it will have its
       // value changed to match the specified value. If there is no ZHTML_Attribute
       // with the specified name, one will be created. This method is case-insensitive.
       // Parm: csName - Name of the ZHTML_Attribute to edit or create.  Case-insensitive.
       // Parm: csValue - Value to be held in this attribute.
       // Parm: chDelimiter - Delimiter to surround this attribute (usually quote or apostrophe).
       // Parm: lFlag - 0 - Set/Replace current value of this attribute.
       //               1 - Append value to current value of this attribute.
       //               2 - Append value to current, resetting delimiter to encompass entire value
       void
       SetAttributeProperties( String csName, String csValue, char chDelimiter, int lFlag )
       {
          if ( StringUtils.isBlank( csName ) == false )
          {
             csName = csName.toLowerCase( );

          // if ( csValue.isBlank( ) )
          //    csValue = "";

             ZHTML_Attribute pAttribute = SeekAttributeByName( csName );

             if ( pAttribute != null )
             {
                if ( lFlag == 0 )
                {
                   pAttribute.setValue( csValue );
                }
                else
                {
                   String  cs = pAttribute.getValue( );
                   if ( (lFlag & 2) != 0 )
                   {
                      if ( StringUtils.isBlank( cs ) == false )
                         cs += " ";
                   }

                   cs += csValue;
                   pAttribute.setValue( cs );
                }
             }
             else
             {
                pAttribute = new ZHTML_Attribute( csName, csValue, chDelimiter );
                AddAttributeToList( pAttribute );
             }
          }
       }

       private ZHTML_Attribute
       SeekAttributeByName( String csName )
       {
       // Iterator iterator = m_list.listIterator( );
          for( ZHTML_Attribute a : m_list )
          {
             //do something a;
             if ( a.getName( ).equals( csName ) )
                return a;
          }

          return null;
       }

       // Reconstruct the HTML tag.
       private String
       BuildTag( boolean bTagOnly )
       {
          ZHTML_Attribute pAttribute;
          String  csBuffer = "";
          int     k;

          if ( m_bTag )
             csBuffer = "<";

          if ( bTagOnly == false || m_bTag )
             csBuffer += m_csTag;

          if ( bTagOnly == false )
          {
             for ( k = 0; k < m_list.size( ); k++ ) // has attributes
             {
                pAttribute = m_list.get( k );
                csBuffer += " ";
                if ( pAttribute.getValue( ).equals("") )
                {
                   if ( pAttribute.getDelimiter( ) != 0 )
                      csBuffer += pAttribute.getDelimiter( );

                   csBuffer += pAttribute.getName( );
                   if ( pAttribute.getDelimiter( ) != 0 )
                      csBuffer += pAttribute.getDelimiter( );
                }
                else
                {
                   csBuffer += pAttribute.getName( );
                   if ( pAttribute.getValue( ) != "" )
                   {
                      csBuffer += "=";
                      if ( pAttribute.getDelimiter( ) != 0 )
                         csBuffer += pAttribute.getDelimiter( );

                      csBuffer += pAttribute.getValue( );
                      if ( pAttribute.getDelimiter( ) != 0 )
                         csBuffer += pAttribute.getDelimiter( );
                   }
                }
             }
          }

          if ( m_bTag )
             csBuffer += ">";

          return( csBuffer );
       }

       // How many attributes are in this ZHTML_TagAttributeList?
       //int
       //GetCount( )
       //{
       //   return( m_pList ? m_pList.GetCount( ) : 0 );
       //}

       // A list of the attributes in this ZHTML_TagAttributeList.
       //ZMultiIdxSet *
       //GetList( )
       //{
       //   return( m_pList );
       //}

       // Access the individual attributes.
       ZHTML_Attribute
       GetItem( int lIndex )
       {
          if ( lIndex >= 0 && lIndex < m_list.size( ) )
             return( m_list.get( lIndex ) );
          else
             return null;
       }

       // Access the individual attributes by name.
       ZHTML_Attribute
       GetItem( String csName )
       {
          ZHTML_Attribute pAttribute = SeekAttributeByName( csName );
          return( pAttribute );
       }

    // unused stuff ...
    // protected:
//        ZMultiIdxSet *m_pList; // internal vector contains the entire list of attributes

       // Number of attributes in this ZHTML_TagAttributeList.
    // int   GetCount( );

    // // A list of the attributes in this ZHTML_TagAttributeList.
    // ZMultiIdxSet * GetList( );

       // is this used???
    // void CreateZeidonTag( );


    } // end of: private class ZHTML_TagAttributeList


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // The Parse Class
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // Base class for parsing tag based files, such as HTML, HTTP headers, or XML.
    private class ZHTML_Parse
    {
       protected ZHTML_TagAttributeList m_pTAL = null;
       protected String  m_csSource;      // source text that is being parsed
       protected int     m_lSrcLth;       // length of source text that is being parsed
       protected int     m_lIdx;          // current position inside of the text that is being parsed
       protected char    m_chParseDelimiter; // most recently parsed attribute delimiter
       protected char    m_chPrev;        // character prior to current character
       protected String  m_csParseName;   // most recently parsed attribute name
       protected String  m_csParseValue;  // most recently parsed attribute value

       ZHTML_Parse( )
       {
          m_pTAL = null;
          m_lIdx = 0;
          m_chPrev = 0;
       }

       @Override
    protected void finalize( ) throws Throwable
       {
          if ( m_pTAL != null )
             m_pTAL.ClearInit( ); // it should have been deleted by external code
       }

       // Determine if the specified character is whitespace or not.
       // Parm: ch - Character to check
       // Returns true if the character is whitespace.
       boolean
       IsWhiteSpace( char ch )
       {
          return( g_csWhiteSpace.indexOf( ch ) != -1 );
       }

       // Advance the index past any whitespace.
       void
       EatWhiteSpace( )
       {
          char ch;

          while ( Eof( ) == false )
          {
            ch = GetCurrentChar( );
            if ( IsWhiteSpace( ch ) == false )
              return;

            m_chPrev = ch;
            m_lIdx++;
          }
       }

       // Find the next character past any whitespace.  It does not reset the current index (m_lIdx).
       char
       FindNextNonWhiteSpace( )
       {
          int   lIdx = m_lIdx;
          char  chPrev = m_chPrev;
          char  ch;

          EatWhiteSpace( );
          if ( Eof( ) == false && m_lIdx < m_lSrcLth )
            ch = m_csSource.charAt( m_lIdx );
          else
            ch = 0;

          // Reset index and previous character.
          m_lIdx = lIdx;
          m_chPrev = chPrev;

          return( ch );
       }

       // Determine if the end of the source text has been reached.
       // Returns True if the end of the source text has been reached.
       protected boolean Eof( )
       {
          return( m_lIdx >= m_lSrcLth );
       }

       // Parse the attribute name.
       protected void
       ParseAttributeName( )
       {
          char ch = '\0';

          EatWhiteSpace( );

          // Get attribute name.
          while ( Eof( ) == false )
          {
            ch = GetCurrentChar( );
            if ( IsWhiteSpace( ch ) || (ch == '=') || (ch == '>') )
            {
              break;
            }

            m_csParseName += ch;
            m_chPrev = ch;
            m_lIdx++;
          }

          if ( ch == '>' && m_chPrev == '/' )
            m_pTAL.m_nTagType |= 2;  // close

          EatWhiteSpace( );
       }

       // Parse the attribute value.
       void
       ParseAttributeValue( )
       {
          char ch;

          if ( m_chParseDelimiter != 0 )
            return;

          ch = GetCurrentChar( );
          if ( ch == '=' )
          {
            m_chPrev = ch;
            m_lIdx++;
            EatWhiteSpace( );
            ch = GetCurrentChar( );
            if ( (ch == '\'') || (ch == '\"') )
            {
              m_chParseDelimiter = ch;
              m_chPrev = ch;
              m_lIdx++;
              while ( (ch = GetCurrentChar( )) != m_chParseDelimiter )
              {
                m_csParseValue += ch;
                m_chPrev = ch;
                m_lIdx++;
              }

              m_chPrev = ch;
              m_lIdx++;
            }
            else
            {
              while ( Eof( ) == false &&
                    IsWhiteSpace( (ch = GetCurrentChar( )) ) == false &&
                    (ch != '>') )
              {
                m_csParseValue += ch;
                m_lIdx++;
              }
            }

            EatWhiteSpace( );
          }
       }

       // static int t = 0;

       // Add a parsed attribute to the collection.  If the attribute already exists in the collection,
       // the new attribute will supersede the previous attribute.
       void
       AddAttributeToList( )
       {
          int   nRC;

       // t++;
       // if ( t == 29 )
       //    TraceLineI( "AddAttributeToList: ", t );

          ZHTML_Attribute pAttribute = new ZHTML_Attribute( m_csParseName, m_csParseValue, m_chParseDelimiter );
          nRC = m_pTAL.AddAttributeToList( pAttribute );
          if ( nRC != 0 )
          {
            if ( nRC == zMIXSET_DUP )
            {
            // TraceLine( "AddAttributeToList (DUP): 0x%08x  ParseName: %s   ParseValue: %s   Delimiter: %c",
            //            pAttribute, (String ) m_csParseName, (String ) m_csParseValue, m_chParseDelimiter );
              ZHTML_Attribute pA = m_pTAL.SeekAttributeByName( m_csParseName );
              pA.setValue( pAttribute.getValue( ) );
           // delete pAttribute; it will be gced
              return;
            }

            // We have an unknown error.
         // TraceLine( "AddAttributeToList unknown error: %d   Attribute: 0x%08x   ParseName: %s   ParseValue: %s   Delimiter: %c",
         //            nRC, pAttribute, (String ) m_csParseName, (String ) m_csParseValue, m_chParseDelimiter );
         // delete pAttribute; it will be gced
          }
       }

       // Get a few characters ahead of the current character.
       // Parm: name nPeek - How many characters to peek ahead for.
       // Returns - The character that was retrieved.
       protected char
       GetCurrentChar( int   lPeek )
       {
          char ch;

          if ( (m_lIdx + lPeek) < m_lSrcLth )
             ch = m_csSource.charAt( m_lIdx + lPeek );
          else
             ch = 0;

         return( ch );
       }

       // Get the current character that is being parsed.
       protected char
       GetCurrentChar( )
       {
          return( GetCurrentChar( 0 ) );
       }

       // Obtain the next character and advance the index by one.
       // Returns - The next character.
       protected char
       AdvanceCurrentChar( )
       {
          char ch = GetCurrentChar( 0 );
          m_chPrev = ch;
          m_lIdx++;
          return( ch );
       }

       // Move the nIndex forward by one.
       protected void
       Advance( )
       {
          m_chPrev = GetCurrentChar( 0 );
          m_lIdx++;
       }

    } // end of: private class ZHTML_Parse


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // The ParseHTML Class
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public class ZHTML_ParseHTML extends ZHTML_Parse
    {
       private String  m_csDivEntity;          // "div" (zeidon entity)
       private String  m_csDivAttribute;       // "zid" (zeidon attribute)
       private String  m_csTagEntity;          // HTML tag (zeidon entity)
       private String  m_csTagName;            // HTML tag name (zeidon attribute)
       private String  m_csTagValue;           // HTML tag value (zeidon attribute)
       private String  m_csTagLevel;           // HTML tag indentation level (zeidon attribute)
       private String  m_csTagYN;              // Y ==> it is a tag   N ==> not a tag (comment ...) (zeidon attribute)
       private String  m_csTagType;            // 1 ==> start; 2 ==> end; 3 ==> both; 0 ==> not a tag/indeterminate (zeidon attribute)
       private String  m_csAttributeEntity;    // HTML tag attribute (zeidon entity)
       private String  m_csAttributeName;      // HTML tag attribute name (zeidon attribute)
       private String  m_csAttributeValue;     // HTML tag attribute value (zeidon attribute)
       private String  m_csAttributeDelimiter; // HTML tag attribute delimiter (", ', or null) (zeidon attribute)
       private String  m_csSearchAttribute;    // HTML tag attribute to search for while parsing

       private int     m_lFlags;               // 0 - full HTML; 1 - strip invalid tags; 2 - strip properties

       protected ArrayList<ZHTML_TagAttributeList> m_TagList;   // list of tags within this HTML

       // Setters/Getters ...
       // The last attribute name that was encountered.
       private String GetParseName( ) { return( m_csParseName ); }
       private void SetParseName( String csName ) { m_csParseName = csName; }

       // The last attribute value that was encountered.
       private String GetParseValue( ) { return( m_csParseValue ); }
       private void SetParseValue( String csValue ) { m_csParseValue = csValue; }

       // The last attribute delimiter that was encountered.
       private char
       GetParseDelimiter( )
       {
          return( m_chParseDelimiter );
       }

    // private void SetParseDelimiter( String csDelimiter ) { m_chParseDelimiter = csDelimiter; }
       private void
       SetParseDelimiter( char chDelimiter )
       {
          m_chParseDelimiter = chDelimiter;
       }

       // The text that is to be parsed.
       private String
       GetSource( )
       {
          return( m_csSource );
       }

       private void
       SetSource( String csSource )
       {
          m_csSource = csSource;
          m_lSrcLth = m_csSource.length( );
       }

       private void
       SetAttributeList( ZHTML_TagAttributeList pTagAttributeList )
       {
          m_pTAL = pTagAttributeList;
       }

       // ctor
       public ZHTML_ParseHTML( )
       {
          m_TagList = new ArrayList<ZHTML_TagAttributeList>( );
          m_lFlags = 0;
       }

       public void
       ParseHTML_Page( String csPage, String cpcSearchAttribute )
       {
          ZHTML_TagAttributeList pTAL;
          ZHTML_Attribute pAttribute;
          String  csSearch;
          String  cs;
          char    ch;

          if ( StringUtils.isBlank( cpcSearchAttribute ) == false )
          {
             csSearch = cpcSearchAttribute;
          }
          else
          {
             cpcSearchAttribute = null;
             csSearch = "";
          }

          SetSource( csPage );
          while ( Eof( ) == false )
          {
            pTAL = new ZHTML_TagAttributeList( );
            SetAttributeList( pTAL );
            m_TagList.add( m_TagList.size( ), pTAL ); // add tail
            ch = ParseHTML( );
            if ( ch == 0 )
            {
              if ( cpcSearchAttribute != null &&
                   (pAttribute = pTAL.SeekAttributeByName( csSearch )) != null )
              {
                 cs = pTAL.BuildTag( false );
              // TraceLine( "Found '%s': %s  in tag ====> %s",
              //            cpcSearchAttribute, pAttribute.GetValue( ), cs );
              }
            // else
            // {
            //    cs = pTAL.BuildTag( false );
            //    TraceLineS( "No src ==> ", cs );
            // }
            }
          }
       }

       private char
       ParseHTML( )
       {
          char ch;

          while ( true )
          {
             if ( GetCurrentChar( ) == '<' )
             {
                Advance( );

                ch = GetCurrentChar( );
                if ( ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) || (ch == '!') || (ch == '/') )
                {
                   ParseTag( );
                   return( '\0' );
                }
                else
                   return( AdvanceCurrentChar( ) );
             }
             else
             {
             // return( AdvanceCurrentChar( ) );  // originally this was it

                // If this is not just "whitespace", it must be the "value" of a tag.
                ch = FindNextNonWhiteSpace( );
                if ( ch == '<' )
                {
                   EatWhiteSpace( );
                   continue;
                }
                else
                {
                   ParseTagValue( );
                   return( (char) 0 );
                }
             }
          }
       }

       private void
       ParseTag( )
       {
          char ch;
          int  nLth;

       // m_pTAL.ClearInit( );
       // m_pTAL.m_csTag = "";
          m_pTAL.m_bTag = true;
       // m_pTAL.m_nTagType = 0;

          // Is it a comment?
          if ( (GetCurrentChar( ) == '!') &&
               (GetCurrentChar( 1 ) == '-') &&
               (GetCurrentChar( 2 ) == '-') )
          {
             while ( Eof( ) == false )
             {
                ch = GetCurrentChar( );
                if ( (ch == '-') &&
                     (GetCurrentChar( 1 ) == '-') &&
                     (GetCurrentChar( 2 ) == '>') )
                {
                   break;
                }

                if ( ch != '\r' )
                   m_pTAL.m_csTag += ch;

                Advance( );
             }

             m_pTAL.m_csTag += "--";
             Advance( );
             Advance( );
             Advance( );
             m_chParseDelimiter = (char) 0;
             return;
          }

          // Find the tag name.
          while ( Eof( ) == false )
          {
             ch = GetCurrentChar( );
             if ( IsWhiteSpace( ch ) || (ch == '>') )
             {
                break;
             }

             if ( (ch == '>' && m_chPrev == '/') || (ch == '/' && m_chPrev == '<') )
                m_pTAL.m_nTagType |= 2;  // close

             m_pTAL.m_csTag += ch;
             Advance( );
          }

          EatWhiteSpace( );

          // Get the attributes.
          while ( GetCurrentChar( ) != '>' )
          {
             m_csParseName  = "";
             m_csParseValue = "";
             m_chParseDelimiter = (char) 0;

             ParseAttributeName( );

             if ( GetCurrentChar( ) == '>' )
             {
                if ( m_chPrev == '/' )
                   m_pTAL.m_nTagType |= 2;  // close

                AddAttributeToList( );
                break;
             }

             // Get the value (if any).
             ParseAttributeValue( );
             AddAttributeToList( );
          }

          Advance( );

          // Determine type of tag ... open/close.
          nLth = m_pTAL.m_csTag.length( );

          if ( nLth > 0 )
          {
             if ( m_pTAL.m_csTag.charAt( 0 ) == '/' )
                m_pTAL.m_nTagType |= 2;  // close
             else
                m_pTAL.m_nTagType |= 1;  // open

             if ( m_pTAL.m_csTag.charAt( nLth - 1 ) == '/' )
                m_pTAL.m_nTagType |= 2; // close
          }
       }

       // Parse Tag's value.
       private void
       ParseTagValue( )
       {
          char ch;

          m_pTAL.ClearInit( );
       // m_pTAL.m_csTag = "";
       // m_pTAL.m_bTag = false;
       // m_pTAL.m_nTagType = 0;

          // Store the value of this tag.
          while ( Eof( ) == false )
          {
             ch = GetCurrentChar( );
             if ( (ch == '<') )
             {
                break;
             }

             m_pTAL.m_csTag += ch;
             Advance( );
          }
       }

       // Add the specified tag to the list if not already in the list.  Add the
       // specified attribute/value to the tag (override if already present).
       // Returns number of tags found at level (does not include the one added).
       private int
       AddTagAndAttributeToList( String csTag, String cpcAttributeName, String cpcAttributeValue, char chDelimiter, int lFlag )
       {
          ZHTML_TagAttributeList pTAL = null;
          String  csAttributeName = cpcAttributeName;
          String  csAttributeValue = cpcAttributeValue;
          String  csTagName;
          int   nOpen = 0;
          int   nRC = 0;
          int   k;

          // First run through and locate the specified tag.
          for ( k = 0; k < m_TagList.size( ); k++ )
          {
             pTAL = m_TagList.get( k );
             if ( pTAL.m_bTag )
             {
                csTagName = fnTrimTag( pTAL.m_csTag );
                if ( csTag.compareTo( csTagName ) == 0 )
                {
                   if ( (pTAL.m_nTagType & 1) != 0 )  // open
                   {
                      nOpen++;
                      if ( nOpen == 1 )
                      {
                         nRC++;
                         if ( StringUtils.isBlank( cpcAttributeName ) == false )
                         {
                            pTAL.SetAttributeProperties( csAttributeName, csAttributeValue, chDelimiter, lFlag );
                         }
                      }
                   }

                   if ( (pTAL.m_nTagType & 2) != 0 )  // close
                   {
                      nOpen--;
                   }
                }
             }
          }

          if ( nRC == 0 )  // did not find one in list ... so add it
          {
             pTAL = new ZHTML_TagAttributeList( );
             pTAL.m_bTag = true;
             pTAL.m_csTag = "/" + csTag;
             pTAL.m_nTagType = 2;  // close
             SetAttributeList( pTAL );
             m_TagList.add( m_TagList.size( ), pTAL ); // add tail

             pTAL = new ZHTML_TagAttributeList( );
             pTAL.m_bTag = true;
             pTAL.m_csTag = csTag;
             pTAL.m_nTagType = 1;  // open
             SetAttributeList( pTAL );
             m_TagList.add( 0, pTAL ); // add head so it becomes our working pTAL

             if ( StringUtils.isBlank( cpcAttributeName ) == false )
             {
                pTAL.SetAttributeProperties( csAttributeName, csAttributeValue, chDelimiter, lFlag );
             }
          }

          return nRC;
       }

       // Remove the first occurrence of the tag from the HTML text, and return it.
       private String
       RemoveTag( String csPage, String csTag )
       {
          ZHTML_TagAttributeList pTAL = null;
          String csReturnTag = "";
          String  csTagName;
          String  csCurrentTagName = "";
          boolean bTagFound = false;
          int   nFinishUp = 0;
          int   nOpen = 0;
          int   nRC = 0;

          csReturnTag = "";
          csPage = "";

          // Run through and locate the specified tag.  Then remove the tag from its open to close and
          // everything in between.  If the tag is contained by another tag, remove that tag from open
          // to close instead (which will also remove the specified tag).
          while ( m_TagList.size( ) > 0 )
          {
             pTAL = m_TagList.remove( 0 );
             do  // purist's goto
             {
                if ( nFinishUp == 0 )
                {
                   if ( pTAL.m_bTag )
                   {
                      csTagName = fnTrimTag( pTAL.m_csTag );

                      if ( StringUtils.isBlank( csCurrentTagName ) )
                         csCurrentTagName = csTagName;

                      if ( csCurrentTagName.compareTo( csTagName ) == 0 )
                      {
                         if ( (pTAL.m_nTagType & 1) != 0 )  // open
                         {
                            nOpen++;
                            if ( csTag.compareTo( csTagName ) == 0 )
                            {
                               bTagFound = true;
                               if ( nOpen == 1 )
                                  nRC++;
                            }
                         }

                         if ( (pTAL.m_nTagType & 2) != 0 )  // close
                            nOpen--;
                      }
                      else
                      {
                         if ( csTag.compareTo( csTagName ) == 0 )
                         {
                            if ( (pTAL.m_nTagType & 1) != 0 )  // open
                               bTagFound = true;
                         }
                      }

                      if ( bTagFound && nOpen == 0 )
                      {
                         nFinishUp = 1;
                         break;  // exit purist's goto
                      }
                   }
                }

             } while ( false );  // end purist's goto

             if ( nFinishUp == 2 )
             {
                csPage += pTAL.BuildTag( false );
             }
             else
             {
                csReturnTag += pTAL.BuildTag( false );
                if ( nFinishUp == 1 )
                   nFinishUp = 2;
             }

             pTAL.ClearInit( );  // clean up the attribute list (shouldn't be necessary in Java)
          }

          SetAttributeList( null );  // the attribute list has been "eaten up"
          return csReturnTag;
       }

       // Cleanup the current tag/attribute list and get the next tag/attribute list.
       private ZHTML_TagAttributeList
       PopTagList( ZHTML_TagAttributeList pTAL )
       {
          if ( pTAL != null )
             pTAL.ClearInit( );  // clean up the attribute list (shouldn't be necessary in Java)

          if ( m_TagList.size( ) > 0 )
             pTAL = m_TagList.remove( 0 );
          else
             pTAL = null;

          return( pTAL );
       }

       // Add the current tag/attribute list's tag to the output string, cleanup the
       // current tag/attribute list, and get the next tag/attribute list.
       private ZHTML_TagAttributeList
       GetNextInTagList( String cs, ZHTML_TagAttributeList pTAL, String pchValidHTML_TagList )
       {
          if ( pTAL != null )
          {
             if ( pTAL.m_bTag )
             {
                // m_lFlags: 0 - full HTML; 1 - strip invalid tags; 2 - strip properties
                if ( (m_lFlags & 1) == 0 || fnIsTagInList( pTAL.m_csTag, pchValidHTML_TagList ) )
                   cs += pTAL.BuildTag( (m_lFlags & 2) != 0 ? true : false );
             }
             else
             {
             // m_lFlags: 0 - full HTML; 1 - strip invalid tags; 2 - strip properties
             // if ( (m_lFlags & 2) == 0 )
                   cs += pTAL.BuildTag( false );
             }

             pTAL.ClearInit( );  // clean up the attribute list (shouldn't be necessary in Java)
          }

          if ( m_TagList.size( ) > 0 )
          {
             pTAL = m_TagList.remove( 0 );

         /***#if 0  // debugging only
             ZHTML_Attribute pAttribute;
             int   k;
             for ( k = 0; pAttribute = (ZHTML_Attribute) pTAL.GetItem( k ); k++ ) // has attributes
             {
                if ( pAttribute.m_t == 29 )
                   TraceLineS( "Attribute ", "29" );
             }
             #endif***/
          }
          else
             pTAL = null;

          return( pTAL );
       }

       // Right now only traces HTML for testing purposes ... could be put to a file or Blob.
       private void
       ReconstituteHTML( String csHTML, String  cpcValidHTML_TagList,
                         int    lFlags ) // 0 - full HTML; 1 - strip invalid tags; 2 - strip properties

       {
          ZHTML_TagAttributeList pTAL = null;
          Stack<ZNameItem> TagStack = new Stack<ZNameItem>( );  // tag stack (not guaranteed to be unique)
          String pchValidHTML_TagList = cpcValidHTML_TagList;
          String  csSpace = "                                                                                                    ";
          String  cs;
          int     lLevel = 0;
          int     lRC;  // just for debugging ... not used

 //???    TraceLineS( "", "" );
 //???    TraceLineS( "", "" );
 //???    TraceLineS( "Original HTML ==========================================================> ", csHTML );

          m_lFlags = lFlags;
          csHTML = "";
          pTAL = GetNextInTagList( csHTML, pTAL, pchValidHTML_TagList );
          while ( pTAL != null )
          {
             if ( (pTAL.m_nTagType & 2) != 0 )  // close
                lRC = fnCompareTags( pTAL, TagStack, lLevel );

             if ( pTAL.m_bTag && (pTAL.m_nTagType & 1) != 0 &&  // open tag
                  ((m_lFlags & 1) == 0 || fnIsTagInList( pTAL.m_csTag, pchValidHTML_TagList )) )
             {
                cs = "";  // "\n" + csSpace.Left( lLevel * 3 );
             }
             else
                cs = "";

             // Determine if we are opening or closing a tag ... and alter level accordingly.
             if ( pTAL.m_bTag == true && pTAL.m_nTagType != 0 )
             {
                if ( (pTAL.m_nTagType & 1) != 0 )  // open
                {
                   TagStack.add( 0, new ZNameItem( pTAL.m_csTag, 0 ) );  // AddHead;
                   if ( (pTAL.m_nTagType & 2) == 0 )  // if it's not an open/close combination
                      lLevel++;

                // fnCheckStack( "After Open", TagStack, -1 );
                }
             }

             pTAL = GetNextInTagList( cs, pTAL, pchValidHTML_TagList );
             if ( pTAL != null )
             {
                // If it's not a tag, just add it (value of the tag) onto the output string and
                // then immediately try to add on the close of the tag.
                if ( pTAL.m_bTag == false )
                {
                   pTAL = GetNextInTagList( cs, pTAL, pchValidHTML_TagList );
                   if ( pTAL != null )
                   {
                      if ( pTAL.m_bTag && (pTAL.m_nTagType == 2) )  // strictly a close
                      {
                         lRC = fnCompareTags( pTAL, TagStack, lLevel );
                         pTAL = GetNextInTagList( cs, pTAL, pchValidHTML_TagList );
                         fnCheckStack( "After Value", TagStack, -1 );
                      }
                   }
                }
             }

          // TraceLineS( " | ", cs );
             csHTML += cs;
          }

 //???    TraceLineS( "Reconstituted HTML =====================================================> ", csHTML );
 //???    TraceLineS( "", "" );
          SetAttributeList( null );  // the attribute list has been "eaten up"
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       ///////////////////////////////////////////////////////////////////////////////////////////////////////

       private void
       CreateTagOI( ZHTML_TagAttributeList pTAL,
                    View   vTgt,
                    int    lLevel )
       {
          ZHTML_Attribute pAttribute;
          Character chDelimiter;
          int     k;

          CreateEntity( vTgt, m_csTagEntity, zPOS_AFTER );
          SetAttributeFromString( vTgt, m_csTagEntity, m_csTagName, pTAL.m_csTag );
          SetAttributeFromString( vTgt, m_csTagEntity, m_csTagYN, pTAL.m_bTag ? "Y" : "N" );
          SetAttributeFromInteger( vTgt, m_csTagEntity, m_csTagType, pTAL.m_nTagType );
          SetAttributeFromInteger( vTgt, m_csTagEntity, m_csTagLevel, lLevel );

          for ( k = 0; k < pTAL.m_list.size( ); k++ ) // has attributes
          {
             pAttribute = pTAL.GetItem( k );
             CreateEntity( vTgt, m_csAttributeEntity, zPOS_AFTER );
             SetAttributeFromString( vTgt, m_csAttributeEntity, m_csAttributeName, pAttribute.getName( ) );
             SetAttributeFromString( vTgt, m_csAttributeEntity, m_csAttributeValue, pAttribute.getValue( ) );
             chDelimiter = pAttribute.getDelimiter( );
             SetAttributeFromString( vTgt, m_csAttributeEntity, m_csAttributeDelimiter, chDelimiter.toString( ) );
          }
       }

       // Populate Zeidon OI from HTML.
       private void
       HTML_ToZeidonOI( View vTgt )
       {
          ZHTML_TagAttributeList pTAL = null;
          ZHTML_Attribute pAttribute;
          Stack<ZNameItem> TagStack = new Stack<ZNameItem>( );  // tag stack (not guaranteed to be unique)
          ZNameItem pNameItem;
          String  cszid = "zid";
          String  cs;
          boolean bCheck = true; // need a Div entity to start things off
          int     lLevel = 0;
          int     lFlag;
          int     lCnt = 0;

          while ( CheckExistenceOfEntity( vTgt, m_csDivEntity ) == zCURSOR_SET )
             DeleteEntity( vTgt, m_csDivEntity, zREPOS_FIRST );

          TraceLineS( "", "" );
          TraceLineS( "", "" );
          TraceLineS( "HTML to Zeidon OI", "==========================================================" );
          TraceLineS( "HTML to Zeidon OI", "==========================================================" );
          TraceLineS( "", "" );

          pTAL = PopTagList( pTAL );
          while ( pTAL != null )
          {
             lCnt++;
             pNameItem = null;

             // Determine if we are opening or closing a tag ... and alter level accordingly.
             if ( pTAL.m_bTag == true && pTAL.m_nTagType != 0 )
             {
                if ( (pTAL.m_nTagType & 1) != 0 )  // open
                {
                   pNameItem = new ZNameItem( pTAL.m_csTag, 0 );
                   TagStack.add( 0, pNameItem );  // AddHead;
                   lLevel++;
                   fnCheckStack( "After Open", TagStack, lCnt );
                }
             }

             pAttribute = pTAL.SeekAttributeByName( cszid );
             if ( pAttribute != null )
             {
                bCheck = false;
                if ( pNameItem != null )
                   pNameItem.setFlag( 1 ); // we are on a generated div

                CreateEntity( vTgt, m_csDivEntity, zPOS_AFTER );
                SetAttributeFromString( vTgt, m_csDivEntity, m_csDivAttribute, pAttribute.getValue( ) );
             }
             else
             {
                if ( bCheck && CheckExistenceOfEntity( vTgt, m_csDivEntity ) != zCURSOR_SET )
                {
                   bCheck = false;
                   CreateEntity( vTgt, m_csDivEntity, zPOS_AFTER );
                // SetAttributeFromString( vTgt, m_csDivEntity, m_csDivAttribute, ".NoDiv." );
                }

                CreateTagOI( pTAL, vTgt, lLevel );
                if ( pTAL.m_nTagType == 3 ) // open and close
                   lLevel--;
             }

             if ( pTAL.m_nTagType == 2 )  // strictly a close
             {
                lFlag = fnCompareTags( pTAL, TagStack, lLevel );
                if ( lFlag == 1 )
                {
                   CreateEntity( vTgt, m_csAttributeEntity, zPOS_AFTER );
                   SetAttributeFromString( vTgt, m_csAttributeEntity, m_csAttributeName, "<zid>" );
                }
             }

             pTAL = PopTagList( pTAL );
             if ( pTAL != null )
             {
                // If it's not a tag, just add it (value of the tag) on to the output
                // string and then immediately try to add on the close of the tag.
                if ( pTAL.m_bTag == false )
                {
                   lLevel++;
                   CreateTagOI( pTAL, vTgt, lLevel );
                   pTAL = PopTagList( pTAL );
                   lLevel--;
                   if ( pTAL != null )
                   {
                      if ( pTAL.m_bTag && (pTAL.m_nTagType == 2) )  // strictly a close
                      {
                         CreateTagOI( pTAL, vTgt, lLevel );
                         lFlag = fnCompareTags( pTAL, TagStack, lLevel );
                         if ( lFlag == 1 )
                         {
                            CreateEntity( vTgt, m_csAttributeEntity, zPOS_AFTER );
                            SetAttributeFromString( vTgt, m_csAttributeEntity, m_csAttributeName, "<zid>" );
                         }

                         pTAL = PopTagList( pTAL );
                         fnCheckStack( "After Value", TagStack, lCnt );
                      }
                   }
                }
             }
          }

          SetAttributeList( null );  // the attribute list has been "eaten up"
       }

       private String
       ConstructTag( View vSrc, String csPage, String csLastTag )
       {
          String  csSpace = "                                                                                                    ";
          String  csTag;
          String  cs;
          String  pchTag = null;
          String  pchValue = null;
          String  pch = null;
          String  chDelimiter = null;
          int     lLevel = 0;
          int     lTagType = 0;
          int     nRC;

          nRC = SetCursorFirstEntity( vSrc, m_csTagEntity, "" );
          while ( nRC == zCURSOR_SET )
          {
             lTagType = GetIntegerFromAttribute( lTagType, vSrc, m_csTagEntity, m_csTagType );
             pch = GetAddrForAttribute( pch, vSrc, m_csTagEntity, m_csTagYN );
             lLevel = GetIntegerFromAttribute( lLevel, vSrc, m_csTagEntity, m_csTagLevel );
             pchTag = GetAddrForAttribute( pchTag, vSrc, m_csTagEntity, m_csTagName );
             if ( pch.length( ) > 0 && pch.charAt( 0 ) == 'Y' )
             {
                cs = pchTag;
                csTag = fnTrimTag( cs );
                if ( csTag.compareTo( csLastTag ) == 0 )
                {
                   csLastTag = "";
                }
                else
                {
                   csPage += "\r\n";
                   csPage += csSpace.substring( 0, lLevel * 3 );
                }

                csPage += '<';
                csPage += pchTag;

                if ( (lTagType & 1) != 0 )
                {
                   nRC = SetCursorFirstEntity( vSrc, m_csAttributeEntity, "" );
                   while ( nRC == zCURSOR_SET )
                   {
                      chDelimiter = GetStringFromAttribute( chDelimiter, vSrc, m_csAttributeEntity, m_csAttributeDelimiter );
                      pch = GetAddrForAttribute( pch, vSrc, m_csAttributeEntity, m_csAttributeName );
                      pchValue = GetAddrForAttribute( pchValue, vSrc, m_csAttributeEntity, m_csAttributeValue );
                      csPage += ' ';
                      csPage += pch;
                      if ( pchValue.isEmpty( ) == false )
                      {
                         csPage += '=';
                         csPage += chDelimiter;
                         csPage += pchValue;
                         csPage += chDelimiter;
                      }

                      nRC = SetCursorNextEntity( vSrc, m_csAttributeEntity, "" );
                   }
                }

                csPage += '>';
                csLastTag = pchTag;
             }
             else
             {
                csPage += pchTag;
             }

             nRC = SetCursorNextEntity( vSrc, m_csTagEntity, "" );
          }

          return csPage;
       }

       // Reconstruct HTML from Zeidon OI.
       private String
       ZeidonOI_ToHTML( View vSrc )
       {
          ZHTML_TagAttributeList pTAL = null;
       // Stack<String> TagStack;  // tag stack (not guaranteed to be unique)
          String  csTag;
          String  csLastTag = "div";
          String  pch = null;
          String  csPage = "";
          int    nRC;

          TraceLineS( "", "" );
          TraceLineS( "", "" );
          TraceLineS( "Zeidon OI to HTML", "==========================================================" );
          TraceLineS( "Zeidon OI to HTML", "==========================================================" );
          TraceLineS( "", "" );

          nRC = SetCursorFirstEntity( vSrc, m_csDivEntity, "" );
          while ( nRC == zCURSOR_SET )
          {
             pch = GetAddrForAttribute( pch, vSrc, m_csDivEntity, m_csDivAttribute );

             if ( csLastTag.compareTo( "div" ) != 0 )
                csPage += "\r\n";

             csPage += "<div zid=\"";
             csPage += pch;
             csPage += "\" style=\"border:0px; margin:0px;\">";

             csPage = ConstructTag( vSrc, csPage, csLastTag );

          // csPage += "\r\n</div>";
             nRC = SetCursorNextEntity( vSrc, m_csDivEntity, "" );
          }

          return( csPage );
       }


    } // end of: private class ZHTML_ParseHTML


       private static void
       fnCheckStack( String cpcText, Stack<ZNameItem> TagStack, int   lCnt )
       {
          /*
          ZNameItem pNameItem = TagStack.GetHead( );
          int     lLevel = 0;
          String  csSpace = "                                                                                                    ";
          String  cs;

          TraceLine( "%4d - fnCheckStack: %s", lCnt, cpcText );
          while ( pNameItem )
          {
             lLevel++;
             cs = csSpace.Left( lLevel * 3 );
             TraceLineS( cs, pNameItem.getName( ) );
             pNameItem = pNameItem.m_pNextName;
          }
          */
       }

       // Remove the close (/) character and remove leading/trailing whitespace from the tag.
       private static String
       fnTrimTag( String csTag )
       {
          if ( csTag.length( ) > 0 )
          {
             if ( csTag.charAt( 0 ) == '/' )
                csTag = csTag.substring( 1 );
             else
             if ( csTag.charAt( csTag.length( ) - 1 ) == '/' )
                csTag = csTag.substring( 0, csTag.length( ) - 1 );

             csTag = csTag.trim( );
          }

          return( csTag );
       }

       private static boolean
       fnIsTagInList( String  stringTagName, String stringValidHTML_TagList )
       {
          String  pchTag;
       // String  pchComma;
          int     nComma;
          int     nCommaPrev = 0;
          boolean bFound = false;

          stringTagName = fnTrimTag( stringTagName );
          pchTag = stringValidHTML_TagList;
          while ( pchTag != null )
          {
             nComma = zstrchr( pchTag, ',' );
             if ( nComma >= 0 )
                pchTag = stringValidHTML_TagList.substring( nCommaPrev, nComma - 1 );

             if ( zstrcmp( stringTagName, pchTag ) == 0 )
             {
                bFound = true;
                break;
             }

             if ( nComma >= 0 )
             {
                nCommaPrev = nComma + 1;
             }
             else
                pchTag = null;
          }

          return bFound;
       }

       // Compare the tags from the attribute list and the tag stack after
       // trimming the close (/) tag character.  If they are the same,
       // decrement the indentation level.
       private int
       fnCompareTags( ZHTML_TagAttributeList pTAL, Stack<ZNameItem> TagStack, int lLevel )   //??? level needs to be returned!!!
       {
          String  csTag = fnTrimTag( pTAL.m_csTag );
          ZNameItem pNameItem;
          int     lRC = 0;

          if ( TagStack.isEmpty( ) == false && (pNameItem = TagStack.remove( 0 )) != null )  // GetHead( );
          {
             if ( pNameItem.getName( ).compareTo( csTag ) != 0 )
             {
                TraceLine( "Error in Tag Stack: %s ... expected: %s", pNameItem.getName( ), csTag );
             }

             lRC = pNameItem.getFlag( );
             lLevel--;
          // fnCheckStack( "After Drop", TagStack, -1 );
          }
          else
          {
             TraceLine( "Error in Tag Stack: unmatched close: %s", csTag );
          }

          return lRC;
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       //
       // ParseHTML_TextToZeidonOI
       //
       //    cpcHTML               raw HTML
       //    TgtView               target view into which to parse HTML
       //    TgtDivEntity          target "div" entity
       //    TgtDivAttribute       target "zid" attribute
       //    TgtTagEntity          target HTML tag entity
       //    TgtTagName            HTML tag entity name
       //    TgtTagValue           HTML tag entity value
       //    TgtTagLevel           HTML tag entity indentation level
       //    TgtTagYN              Y ==> it is a tag   N ==> not a tag (comment ...)
       //    TgtTagType            1 ==> start; 2 ==> end; 3 ==> both; 0 ==> not a tag/indeterminate
       //    TgtAttributeEntity    target HTML tag attribute entity
       //    TgtAttributeName      HTML tag attribute name
       //    TgtAttributeValue     HTML tag attribute value
       //    TgtAttributeDelimiter HTML tag attribute delimiter (", ', or null)
       //
       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       public int
       ParseHTML_TextToZeidonOI( String  cpcHTML,
                                 View   vHTML_TgtView,
                                 String  cpcHTML_TgtDivEntity,
                                 String  cpcHTML_TgtDivAttribute,
                                 String  cpcHTML_TgtTagEntity,
                                 String  cpcHTML_TgtTagName,
                                 String  cpcHTML_TgtTagValue,
                                 String  cpcHTML_TgtTagLevel,
                                 String  cpcHTML_TgtTagYN,
                                 String  cpcHTML_TgtTagType,
                                 String  cpcHTML_TgtAttributeEntity,
                                 String  cpcHTML_TgtAttributeName,
                                 String  cpcHTML_TgtAttributeValue,
                                 String  cpcHTML_TgtAttributeDelimiter )
       {
          ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );
       // Stack<ZNameItem> TagStack;  // tag stack (not guaranteed to be unique)
          String  csPage;

          csPage = cpcHTML;
          pParseHTML.SetAttributeList( null );
          pParseHTML.ParseHTML_Page( csPage, "" );

          // HTML to Zeidon OI.

          pParseHTML.m_csDivEntity = cpcHTML_TgtDivEntity;
          pParseHTML.m_csDivAttribute = cpcHTML_TgtDivAttribute;
          pParseHTML.m_csTagEntity = cpcHTML_TgtTagEntity;
          pParseHTML.m_csTagName = cpcHTML_TgtTagName;
          pParseHTML.m_csTagValue = cpcHTML_TgtTagValue;
          pParseHTML.m_csTagLevel = cpcHTML_TgtTagLevel;
          pParseHTML.m_csTagYN = cpcHTML_TgtTagYN;
          pParseHTML.m_csTagType = cpcHTML_TgtTagType;
          pParseHTML.m_csAttributeEntity = cpcHTML_TgtAttributeEntity;
          pParseHTML.m_csAttributeName = cpcHTML_TgtAttributeName;
          pParseHTML.m_csAttributeValue = cpcHTML_TgtAttributeValue;
          pParseHTML.m_csAttributeDelimiter = cpcHTML_TgtAttributeDelimiter;

          pParseHTML.HTML_ToZeidonOI( vHTML_TgtView );
          // done with pParseHTML
          return 0;
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       //
       // ParseHTML_AttrToZeidonOI
       //
       //    SrcView               source view to get raw HTML
       //    SrcEntity             source entity
       //    SrcAttribute          source attribute containing raw HTML
       //    TgtView               target view into which to parse HTML
       //    TgtDivEntity          target "div" entity
       //    TgtDivAttribute       target "zid" attribute
       //    TgtTagEntity          target HTML tag entity
       //    TgtTagName            HTML tag entity name
       //    TgtTagValue           HTML tag entity value
       //    TgtTagLevel           HTML tag entity indentation level
       //    TgtTagYN              Y ==> it is a tag   N ==> not a tag (comment ...)
       //    TgtTagType            1 ==> start; 2 ==> end; 3 ==> both; 0 ==> not a tag/indeterminate
       //    TgtAttributeEntity    target HTML tag attribute entity
       //    TgtAttributeName      HTML tag attribute name
       //    TgtAttributeValue     HTML tag attribute value
       //    TgtAttributeDelimiter HTML tag attribute delimiter (", ', or null)
       //
       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       public int
       ParseHTML_AttrToZeidonOI( View   vHTML_SrcView,
                                 String  cpcHTML_SrcEntity,
                                 String  cpcHTML_SrcAttribute,
                                 View   vHTML_TgtView,
                                 String  cpcHTML_TgtDivEntity,
                                 String  cpcHTML_TgtDivAttribute,
                                 String  cpcHTML_TgtTagEntity,
                                 String  cpcHTML_TgtTagName,
                                 String  cpcHTML_TgtTagValue,
                                 String  cpcHTML_TgtTagLevel,
                                 String  cpcHTML_TgtTagYN,
                                 String  cpcHTML_TgtTagType,
                                 String  cpcHTML_TgtAttributeEntity,
                                 String  cpcHTML_TgtAttributeName,
                                 String  cpcHTML_TgtAttributeValue,
                                 String  cpcHTML_TgtAttributeDelimiter )
       {
          String  pchHTML = null;
       // String  csPage;

          pchHTML = GetAddrForAttribute( pchHTML, vHTML_SrcView, cpcHTML_SrcEntity, cpcHTML_SrcAttribute );
          if ( StringUtils.isBlank( pchHTML ) )
          {
             TraceLine( "ParseHTML_ToZeidon HTML is empty for Entity.Attribute: %s.%s",
                        cpcHTML_SrcEntity, cpcHTML_SrcAttribute );
          }
          else
          {
             TraceLine( "ParseHTML_ToZeidon HTML looking for 'zid' in HTML from Entity.Attribute: %s.%s Lth: %d ==> %s",
                        cpcHTML_SrcEntity, cpcHTML_SrcAttribute, zstrlen( pchHTML), pchHTML );
          }

          return( ParseHTML_TextToZeidonOI( pchHTML, vHTML_TgtView, cpcHTML_TgtDivEntity, cpcHTML_TgtDivAttribute,
                                            cpcHTML_TgtTagEntity, cpcHTML_TgtTagName, cpcHTML_TgtTagValue,
                                            cpcHTML_TgtTagLevel, cpcHTML_TgtTagYN, cpcHTML_TgtTagType,
                                            cpcHTML_TgtAttributeEntity, cpcHTML_TgtAttributeName, cpcHTML_TgtAttributeValue,
                                            cpcHTML_TgtAttributeDelimiter ) );
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       //
       // ConstructHTML_TextFromZeidonOI
       //
       //    TgtView             target view for HTML
       //    TgtEntity           target entity for HTML
       //    TgtAttribute        target attribute for HTML
       //    SrcView             source view into which to parse HTML
       //    SrcDivEntity        source "div" entity
       //    SrcDivAttribute     source "zid" attribute
       //    SrcTagEntity        source HTML tag entity
       //    SrcTagName          HTML tag entity name
       //    SrcTagValue         HTML tag entity value
       //    SrcTagLevel         HTML tag entity indentation level
       //    SrcTagYN            Y ==> it is a tag   N ==> not a tag (comment ...)
       //    SrcTagType          1 ==> start; 2 ==> end; 3 ==> both; 0 ==> not a tag/indeterminate
       //    SrcAttributeEntity  source HTML tag attribute entity
       //    SrcAttributeName    HTML tag attribute name
       //    SrcAttributeValue   HTML tag attribute value
       //    SrcAttributeDelimiter HTML tag attribute delimiter (", ', or null)
       //
       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       public int
       ConstructHTML_TextFromZeidonOI( StringBuilder sbHTML_Text,
                                       int    ulMaxLth,
                                       View   vHTML_SrcView,
                                       String cpcHTML_SrcDivEntity,
                                       String cpcHTML_SrcDivAttribute,
                                       String cpcHTML_SrcTagEntity,
                                       String cpcHTML_SrcTagName,
                                       String cpcHTML_SrcTagValue,
                                       String cpcHTML_SrcTagLevel,
                                       String cpcHTML_SrcTagYN,
                                       String cpcHTML_SrcTagType,
                                       String cpcHTML_SrcAttributeEntity,
                                       String cpcHTML_SrcAttributeName,
                                       String cpcHTML_SrcAttributeValue,
                                       String cpcHTML_SrcAttributeDelimiter ) throws IOException
       {
          ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );
       // Stack<ZNameItem> TagStack;  // tag stack (not guaranteed to be unique)
          int   nRC;

          // Zeidon OI to HTML.

          pParseHTML.m_csDivEntity = cpcHTML_SrcDivEntity;
          pParseHTML.m_csDivAttribute = cpcHTML_SrcDivAttribute;
          pParseHTML.m_csTagEntity = cpcHTML_SrcTagEntity;
          pParseHTML.m_csTagName = cpcHTML_SrcTagName;
          pParseHTML.m_csTagValue = cpcHTML_SrcTagValue;
          pParseHTML.m_csTagLevel = cpcHTML_SrcTagLevel;
          pParseHTML.m_csTagYN = cpcHTML_SrcTagYN;
          pParseHTML.m_csTagType = cpcHTML_SrcTagType;
          pParseHTML.m_csAttributeEntity = cpcHTML_SrcAttributeEntity;
          pParseHTML.m_csAttributeName = cpcHTML_SrcAttributeName;
          pParseHTML.m_csAttributeValue = cpcHTML_SrcAttributeValue;
          pParseHTML.m_csAttributeDelimiter = cpcHTML_SrcAttributeDelimiter;

          String csPage = pParseHTML.ZeidonOI_ToHTML( vHTML_SrcView );

          zstrncpy( sbHTML_Text, csPage, ulMaxLth );
          if ( ulMaxLth < csPage.length( ) )
             nRC = 1;
          else
             nRC = 0;

          // Remove these lines prior to deployment!!!
          TraceLineS( "ConstructHTML_FromZeidonOI: ", csPage );

          KZOEP1AA m_KZOEP1AA = new KZOEP1AA( vHTML_SrcView );
          int  lFileHTML = m_KZOEP1AA.SysOpenFile( vHTML_SrcView, "c:\\temp\\test.html", COREFILE_WRITE );
          m_KZOEP1AA.SysWriteFile( vHTML_SrcView, lFileHTML, csPage, csPage.length( ) );
          m_KZOEP1AA.SysCloseFile( vHTML_SrcView, lFileHTML, 0 );
          // End of: Remove these lines prior to deployment!!!

          // done with pParseHTML
          return nRC;
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       //
       // ConstructHTML_AttrFromZeidonOI
       //
       //    TgtView             target view for HTML
       //    TgtEntity           target entity for HTML
       //    TgtAttribute        target attribute for HTML
       //    SrcView             source view into which to parse HTML
       //    SrcDivEntity        source "div" entity
       //    SrcDivAttribute     source "zid" attribute
       //    SrcTagEntity        source HTML tag entity
       //    SrcTagName          HTML tag entity name
       //    SrcTagValue         HTML tag entity value
       //    SrcTagLevel         HTML tag entity indentation level
       //    SrcTagYN            Y ==> it is a tag   N ==> not a tag (comment ...)
       //    SrcTagType          1 ==> start; 2 ==> end; 3 ==> both; 0 ==> not a tag/indeterminate
       //    SrcAttributeEntity  source HTML tag attribute entity
       //    SrcAttributeName    HTML tag attribute name
       //    SrcAttributeValue   HTML tag attribute value
       //    SrcAttributeDelimiter HTML tag attribute delimiter (", ', or null)
       //
       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       public int
       ConstructHTML_AttrFromZeidonOI( View    vHTML_TgtView,
                                       String  cpcHTML_TgtEntity,
                                       String  cpcHTML_TgtAttribute,
                                       View    vHTML_SrcView,
                                       String  cpcHTML_SrcDivEntity,
                                       String  cpcHTML_SrcDivAttribute,
                                       String  cpcHTML_SrcTagEntity,
                                       String  cpcHTML_SrcTagName,
                                       String  cpcHTML_SrcTagValue,
                                       String  cpcHTML_SrcTagLevel,
                                       String  cpcHTML_SrcTagYN,
                                       String  cpcHTML_SrcTagType,
                                       String  cpcHTML_SrcAttributeEntity,
                                       String  cpcHTML_SrcAttributeName,
                                       String  cpcHTML_SrcAttributeValue,
                                       String  cpcHTML_SrcAttributeDelimiter ) throws IOException
       {
       // ZHTML_ParseHTML pParseHTML = new ZHTML_ParseHTML( );
       // Stack<ZNameItem> TagStack;  // tag stack (not guaranteed to be unique)
       // String  csPage;
          StringBuilder  sbHTML_Text;
          MutableInt nLth = new MutableInt( 0 );
          int    ulLth = 0;
          int    nRC;

          GetAttributeLength( nLth, vHTML_TgtView, cpcHTML_TgtEntity, cpcHTML_TgtAttribute );
          ulLth = nLth.intValue( );
          sbHTML_Text = new StringBuilder( ulLth + 1 );
          nRC = ConstructHTML_TextFromZeidonOI( sbHTML_Text, ulLth,
                                                vHTML_SrcView,
                                                cpcHTML_SrcDivEntity,
                                                cpcHTML_SrcDivAttribute,
                                                cpcHTML_SrcTagEntity,
                                                cpcHTML_SrcTagName,
                                                cpcHTML_SrcTagValue,
                                                cpcHTML_SrcTagLevel,
                                                cpcHTML_SrcTagYN,
                                                cpcHTML_SrcTagType,
                                                cpcHTML_SrcAttributeEntity,
                                                cpcHTML_SrcAttributeName,
                                                cpcHTML_SrcAttributeValue,
                                                cpcHTML_SrcAttributeDelimiter );

          SetAttributeFromString( vHTML_TgtView, cpcHTML_TgtEntity, cpcHTML_TgtAttribute, sbHTML_Text );

          // done with pchHTML_Text
          // done with pParseHTML
          return nRC;
       }

       // Load the next "attribute" from the line (read from the CSV "file").
       private String
       fnGetNextAttribute( String pchLine, StringBuilder sbAttributeValue, int lLineNbr )
       {
          String pchEnd = null;  // no more left to parse
          sbAttributeValue.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.

          if ( StringUtils.isBlank( pchLine ) == false )
          {
             if ( pchLine.charAt( 0 ) == '"' || pchLine.charAt( 0 ) == '\'' )
             {
                char chDelimiter = pchLine.charAt( 0 );
                int  nPosDelimiter = zstrchr( pchLine, 1, chDelimiter ); // skip current delimiter
                int  nPosComma = (nPosDelimiter < pchLine.length( ) - 1) ? nPosDelimiter + 1 : -1;

                if ( nPosDelimiter < 0 || (nPosComma > 0 && pchLine.charAt( nPosComma ) != ',') )
                {
                   // Complain if an error happened.
                   TraceLine( "CSV Quote/Comma Error at line %d parsing CSV value: %s",
                              lLineNbr, pchLine );
                   return null;
                }

                if ( nPosComma > 0 )
                   pchEnd = pchLine.substring( nPosComma + 1 );
                else
                   pchEnd = null;  // no more left to parse

                zstrcpy( sbAttributeValue, pchLine.substring( 1, nPosDelimiter ) );
             }
             else
             {
                int  nPosComma = zstrchr( pchLine, ',' );
                if ( nPosComma >= 0 )
                {
                   pchEnd = pchLine.substring( nPosComma + 1 );
                   if ( nPosComma == 0 )
                      nPosComma = 1;
                }
                else
                   pchEnd = null;  // no more left to parse

                if ( nPosComma > 0 )
                   zstrcpy( sbAttributeValue, pchLine.substring( 0, nPosComma ) );
                else
                   zstrcpy( sbAttributeValue, pchLine );
             }
          }

          return pchEnd;  // if null, nothing left to parse
       }

       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       //
       // Parse list of entity.attribute=value.context specifications.  If only the
       // attribute is specified, no "dot" separators are necessary.  If only
       // entity.attribute is specified, the second "dot" separator may be omitted.
       // If the default entity is to be used and a context is to be specified as
       // well, the specification must be as: .attribute.context
       //
       ///////////////////////////////////////////////////////////////////////////////////////////////////////
       private void
       fnSetEntityAttribList( Stack<ZNameItem> pEntityStack,
                              Stack<ZNameItem> pAttributeStack,
                              Stack<ZNameItem> pValueStack,
                              Stack<ZNameItem> pContextStack,
                              String    cpcDefaultEntity,
                              String    pchEAC )  // Entity.Attribute.Context
       {
          String pch;
          int nDot1;
          int nDot2;
          int nEqual;
          int nComma;
          String pchEntity;
          String pchAttribute;
          String pchAttributeValue;
          String pchContext;

          // So, the choices are:
          //   entity.attribute.context, ... two dots
          //   .attribute.context, ...       two dots (use default entity)
          //   entity.attribute, ...         one dot  (no context)
          //   attribute, ...                no dots  (use default entity, no context)

          pch = pchEAC;
          while ( StringUtils.isBlank( pch ) == false )
          {
             nDot1 = zstrchr( pch, '.' );
             nComma = zstrchr( pch, ',' );
             if ( nDot1 >= 0 )
             {
                nDot2 = zstrchr( pch, nDot1 + 1, '.' );
                if ( nComma >= 0 )
                {
                   if ( nDot1 > nComma )  // ==> no dots
                   {
                      nDot1 = -1;
                      nDot2 = -1;
                   }
                   else
                   {
                      if ( nDot2 > nComma )  // ==> one dot
                         nDot2 = -1;
                   }
                }
             }
             else
             {
                nDot2 = -1;  // ==> no dots
             }

             // Work out Entity.Attribute.Context ...
             if ( nDot2 >= 0 )  // we have EAC (possibly with default E)
             {
                if ( nDot1 > 0 )
                   pchEntity = pch.substring( 0, nDot1 );
                else
                   pchEntity = cpcDefaultEntity;

                pchAttribute = pch.substring( nDot1 + 1, nDot2 );
                if ( nComma > 0 )
                   pchContext = pch.substring( nDot2 + 1, nComma );
                else
                   pchContext = pch.substring( nDot2 + 1 );
             }
             else
             if ( nDot1 >= 0 )
             {
                pchContext = null;
                if ( nDot1 > 0 )
                   pchEntity = pch.substring( 0, nDot1 );
                else
                   pchEntity = cpcDefaultEntity;

                if ( nComma > 0 )
                   pchAttribute = pch.substring( nDot1 + 1, nComma );
                else
                   pchAttribute = pch.substring( nDot1 + 1 );
             }
             else
             {
                pchEntity = cpcDefaultEntity;
                pchContext = null;

                if ( nComma >= 0 )
                   pchAttribute = pch.substring( 0, nComma );
                else
                   pchAttribute = pch;
             }

             // Now check for Attribute=Value
             nEqual = zstrchr( pchAttribute, '=' );
             if ( nEqual > 0 )
             {
                pchAttributeValue = pchAttribute.substring( nEqual + 1 );
                pchAttribute = pchAttribute.substring( 0, nEqual );
             }
             else
                pchAttributeValue = null;

             pEntityStack.add( pEntityStack.size( ), new ZNameItem( pchEntity, 0 ) );  // AddTail( pch );
             pAttributeStack.add( pAttributeStack.size( ), new ZNameItem( pchAttribute, 0 ) );  // AddTail( pch );

             if ( pchAttributeValue == null )
                pValueStack.add( pValueStack.size( ), new ZNameItem( "", 0 ) );  // AddTail( pch );
             else
                pValueStack.add( pValueStack.size( ), new ZNameItem( pchAttributeValue, 1 ) );  // AddTail( pch );

             if ( pchContext == null )
                pContextStack.add( pContextStack.size( ), new ZNameItem( "", 0 ) );  // AddTail( pch );
             else
                pContextStack.add( pContextStack.size( ), new ZNameItem( pchContext, 0 ) );  // AddTail( pch );

             if ( nComma >= 0 )
                pch = pch.substring( nComma + 1 );
             else
                pch = null;
          }
       }

    public static final int zWINDOW_STATUS_ENABLED_VIEW = 0; // TODO ... DKS???

    public int StartBrowserWindowWithURL( View view, StringBuilder sbReturnFileName, int i, String string, String szFileName, int j )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final void MB_SetMessage( View view, int panelNumber, String message )
    {
       // TODO ... DKS???
    }

    public static final int MapCtrl( View view, String ctrlTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int RefreshVisibleItems( View view, String ctrlTag, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GetCtrlState( View view, String ctrlTag, int statusType )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int SetCtrlState( View view, String ctrlTag, int statusType, int value )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int SetCtrlText( View view, String ctrlTag, String text )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int SetCtrlMapping( View view, String ctrlTag, String string, String string2,
                                            String string3, String string4, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int SetCtrlProperty( View view, String ctrlTag, int i, int lJustification, String columnTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public int SetCtrlRowColText( View ViewToWindow, String ctrlTag, int Row, int Col, String Text )
    {
      /*
      SetCtrlRowColText( zVIEW   vSubtask,
                         zCPCHAR cpcCtrlTag,
                         int     lRow,
                         int     lColumn,
                         zCPCHAR cpcText )
        */

      // TODO - This is driver code.  What are we doing with that?

        return ( 0 );
    }

    public static final int SetWindowState( View view, int statusType, int value )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final void OL_SelectItemAtPosForEntity( View view, String string, String string2, int i )
    {
       // TODO ... DKS???
    }

    public static final int OL_GetCurrentEntityName( View view, String string, View viewApp, StringBuilder sbEntityName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final void OL_SelectItem( View view, String string, String string2, int lKey, int i )
    {
       // TODO ... DKS???
    }

    public static final void SplitFrame( View view, String szSplitName, int i, int j, int k )
    {
       // TODO ... DKS???
    }

    public static final zVIEW AttachSubtaskToPane( View view, String szSplitName, String dialogName, String szDetailWindow, int action )
    {
       // TODO ... DKS???
       return null;
    }

    public static final void GetSubtaskForWindowName( View view, View vSubtask, String string )
    {
       // TODO ... DKS???
    }

    public static final void EnableAction( View view, String string, int true1 )
    {
       // TODO ... DKS???
    }

    public static final void CreateTrackingPopupMenu( View view, String string, int i, int j, int k, int l )
    {
       // TODO ... DKS???
    }

    public static final void OL_SetTextForCurrentItem( View view, String string, String listName )
    {
       // TODO ... DKS???
    }

    public static final int SetFocusToCtrl( View view, String ctrlTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GetCtrlWithFocus( View view, StringBuilder sbCtrlTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int ActivateWindow( View viewActiveDialog )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int RefreshWindowExceptForCtrl( View view, String ctrlTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int FormatSubobjectOnDoc( View view, String string, View viewToWindow, String string2, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int FormatSubobjectOnDocWithXRP( View view, String rootEntityName, View viewToWindow, String string,
                                                         View viewRpt, int printFlag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final String GetLastCtrlTag( View view, String lastCtrl )
    {
       // TODO ... DKS???
       return null;
    }

    public static final int GetCtrlText( View view, String ctrlTag, StringBuilder sbText, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int CloseFrame( View view )
    {
       // TODO ... DKS???
       return 0;
    }

    public int ozqFrame_InitializeFrameForLOD( View view, View queryView )
    {
       // TODO ... DG???
       return 0;
    }

    public static final int SetWindowCaptionTitle( View view, String title, String subTitle )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int StartTask( View view, String directoryName, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int SwitchToAction( View view, String actionTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int InvokeAction( View view, String actionTag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int AddOptionToMenu( View view, String menuTag, String optionTag,
                                             String szMenuName, String szMenuName2, String string3, int i )
    {
    // TODO ... DKS???
       return 0;
    }

    public static final int RemoveOptionFromMenu( View view, String optionTag )
    {
    // TODO ... DKS???
       return 0;
    }

    public static final int GetLastCommandTag( View viewToWindow, StringBuilder sbMenuName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int MapWindow( View view )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int RefreshAllSubwindows( View view )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int CB_GetSelectedString( View view, String ctrlTag, String szValue )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int ED_SetPlaceHolder( View view, String ctrlTag, String string )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int ED_HiliteText( View view, String ctrlTag, int i, int j )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TG_EnableAutosort( View view, String ctrlTag, int flag )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TX_GetTextRptCtrlBOI( View qBaseR, MutableInt miNMultiLineFlag,
                                                  MutableInt nBoldFlag, MutableInt nItalicFlag, MutableInt nStrikeoutFlag, MutableInt nUnderlineFlag,
                                                  MutableInt lJustify, MutableInt lOrigFontSize, MutableInt lColor, MutableInt lTextBkColor,
                                                  MutableInt lBorderStyle, MutableInt lBorderColor, MutableInt lEscapement, MutableInt lType,
                                                  String szFaceName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TX_SetTextRptCtrlBOI( View qBaseR, int nMultiLineFlag,
                                                  int nBoldFlag, int nItalicFlag, int nStrikeoutFlag, int nUnderlineFlag,
                                                  int lJustify, int lFontSize, int lColor, int lTextBkColor,
                                                  int lBorderStyle, int lBorderColor, int lEscapement, int lType,
                                                  String szFaceName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TX_SetColors( View view, String ctrlTag, int i, int j )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GRID_GetActiveCell( View view, String ctrlTag, MutableInt nActiveRow, MutableInt nActiveCol )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GRID_SetActiveCell( View view, String ctrlTag, int nActiveRow, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GRID_SetCheckAttribute( View view, String ctrlTag, int i, String string2, String string3, String string4 )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GRID_SetCellData( View view, String ctrlTag, String szValue, int lRow, int lCol )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int GRID_SetColumnCtrl( View view, String ctrlTag, int i, String string2, int j )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int CB_SetData( View view, String ctrlTag, String szValue, String szEAD, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int LB_SetColorAssociation( View view, String ctrlTag, int i, String string2, int j, int k, int l )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int LB_SetColorAttributeName( View view, String ctrlTag, String string2, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int OL_ExpandEntity( View view, String ctrlTag, String string2, int zexpandall )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int OL_SetDefaultBitmap( View view, String ctrlTag, String string2, String szFullFileName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int OL_SetSelectedBitmap( View view, String ctrlTag, String string2, String szFullFileName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TAB_HideTab( View view, String ctrlTag, int tabNumber, int booleanHideShow )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TAB_HideSubTab( View view, String ctrlTag, String subTabTag,  int booleanHideShow )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final int TAB_GetActiveSubTab( View view, String ctrlTag, StringBuilder sbSubTabName )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final void MapPointSetPushPin( String szAddressLine1, String szCity, String string, String szState,
                                                 String szPostalCode, String string2, int nPinType, int i )
    {
       // TODO ... DKS???
    }

    public static final void MapPointShowApplication( int i )
    {
       // TODO ... DKS???
    }

    public static final int MapPointStart( )
    {
       // TODO ... DKS???
       return 0;
    }

    public static final double GetDistance( double centerLatitude, double centerLongitude,
                                            double targetLatitude, double targetLongitude, int i )
     {
       // TODO ... DKS???
       return 0;
    }

    public static final int GetDistanceRectangle( MutableDouble dLatitudeOut1, MutableDouble dLongitudeOut1,
                                                  MutableDouble dLatitudeOut2, MutableDouble dLongitudeOut2,
                                                  double dLatitudeIn, double dLongitudeIn, double dMiles, int i )
    {
       // TODO ... DKS???
       return 0;
    }

    public int GetHTMLPath( View view, int i, StringBuilder sbPathName )
    {
       // TODO ... DKS???
       return 0;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  DIALOG OPERATION: SetWindowActionBehavior
    //                                               04/01/93 Modified: 04/14/93
    //
    //  PURPOSE:    To override the default control flow action defined for
    //               a window to a new WINDOW. This operation can be used to
    //               change the default flow of control.
    //
    //  PARAMETERS: vSubtask    - The subtask view for the current window
    //               nWindowActionBehavior - this overrides the automatic
    //                                       (default) action behavior specified
    //                                       on the currently executing action..
    //                  valid values are:
    //

    //
    //               stringDlgTag - Dialog name containing new window
    //               stringWndTag - Window name to which to transfer
    //
    //  RETURNS:    0 - Call successful
    //              -1 - Error locating window
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public int SetWindowActionBehavior( View   vSubtask,
                                        int    lWindowBehavior,
                                        String stringDlgTag,
                                        String stringWndTag )
    {
       if ( vSubtask != null )
       {
          View  vKZXMLPGO = vSubtask.getViewByName( "_KZXMLPGO" );
          if ( isValid( vKZXMLPGO ) )
          {
             SetWebRedirection( vKZXMLPGO, lWindowBehavior, stringDlgTag, stringWndTag );
             return 0;
          }
       }

       return -1;
    }

}
