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

    Copyright 2009-2010 QuinSoft
**/

package  com.quinsoft.nazsis;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class nSA_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public nSA_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }

   public int 
   TEST_SAVE_Student( View     ViewToWindow )
   {
      zVIEW    mSAStu = new zVIEW( );
      //:VIEW mSAProf  BASED ON LOD mSAProf  
      zVIEW    mSAProf = new zVIEW( );
      //:VIEW dSATrans BASED ON LOD  dSATrans
      zVIEW    dSATrans = new zVIEW( );
      //:SHORT nRC
      int      nRC = 0;
      //:STRING ( 256 ) szFileName
      String   szFileName = null;
      int      RESULT = 0;


      //:szFileName = "c:\temp\mSAProf.por"
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
         ZeidonStringCopy( sb_szFileName, 1, 0, "c:\\temp\\mSAProf.por", 1, 0, 257 );
      szFileName = sb_szFileName.toString( );}
      //:nRC = ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, szFileName, zMULTIPLE )
      nRC = ActivateOI_FromFile( mSAProf, "mSAProf", ViewToWindow, szFileName, zMULTIPLE );
      //:NAME VIEW mSAProf "mSAProfTEST"
      SetNameForView( mSAProf, "mSAProfTEST", null, zLEVEL_TASK );

      //:SET CURSOR FIRST mSAProf.StudentAccountTransApplied 
      RESULT = SetCursorFirstEntity( mSAProf, "StudentAccountTransApplied", "" );
      //:/*
      //:SET CURSOR FIRST mSAProf.PeriodTransApplied
      //:               WHERE mSAProf.PeriodTransApplied.ID = mSAProf.StudentAccountTransApplied.ID 
      //:IF RESULT >= zCURSOR_SET 
      //:         DropEntity( mSAProf, "PeriodTransApplied", zREPOS_NONE )
      //:END */
      //:DELETE ENTITY  mSAProf.StudentAccountTransApplied
      RESULT = DeleteEntity( mSAProf, "StudentAccountTransApplied", zPOS_NEXT );

      //:COMMIT mSAProf
      RESULT = CommitObjectInstance( mSAProf );
      return( 0 );
   // END
   } 



}
