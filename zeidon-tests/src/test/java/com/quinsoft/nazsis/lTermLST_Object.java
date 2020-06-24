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

package com.quinsoft.nazsis;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlObjectOperations;

/**
   @author QuinSoft
**/

public class lTermLST_Object extends VmlObjectOperations
{
   public lTermLST_Object( View view )
   {
      super( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dYearSemester( VIEW lTermLST BASED ON LOD lTermLST,
//:               STRING ( 32 ) InternalEntityStructure,
//:               STRING ( 32 ) InternalAttribStructure,
//:               SHORT GetOrSetFlag )

//:   STRING ( 50 ) szSemester
public int
olTermLST_dYearSemester( View     lTermLST,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szSemester = null;
   //:STRING ( 50 ) szYearSemester
   String   szYearSemester = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   {
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttributeByContext( szSemester, lTermLST, "CollegeTerm", "Semester", "", 20 )
         {StringBuilder sb_szSemester;
         if ( szSemester == null )
            sb_szSemester = new StringBuilder( 32 );
         else
            sb_szSemester = new StringBuilder( szSemester );
                   GetStringFromAttributeByContext( sb_szSemester, lTermLST, "CollegeTerm", "Semester", "", 20 );
         szSemester = sb_szSemester.toString( );}
         //:szYearSemester = lTermLST.CollegeYear.Year + " " + szSemester
         {StringBuilder sb_szYearSemester;
         if ( szYearSemester == null )
            sb_szYearSemester = new StringBuilder( 32 );
         else
            sb_szYearSemester = new StringBuilder( szYearSemester );
                   GetStringFromAttribute( sb_szYearSemester, lTermLST, "CollegeYear", "Year" );
         szYearSemester = sb_szYearSemester.toString( );}
          {StringBuilder sb_szYearSemester;
         if ( szYearSemester == null )
            sb_szYearSemester = new StringBuilder( 32 );
         else
            sb_szYearSemester = new StringBuilder( szYearSemester );
                  ZeidonStringConcat( sb_szYearSemester, 1, 0, " ", 1, 0, 51 );
         szYearSemester = sb_szYearSemester.toString( );}
          {StringBuilder sb_szYearSemester;
         if ( szYearSemester == null )
            sb_szYearSemester = new StringBuilder( 32 );
         else
            sb_szYearSemester = new StringBuilder( szYearSemester );
                  ZeidonStringConcat( sb_szYearSemester, 1, 0, szSemester, 1, 0, 51 );
         szYearSemester = sb_szYearSemester.toString( );}


         //:// Store the calculated value in the object.
         //:StoreStringInRecord ( lTermLST,
         //:                    InternalEntityStructure, InternalAttribStructure, szYearSemester )
         StoreStringInRecord( lTermLST, InternalEntityStructure, InternalAttribStructure, szYearSemester );
         break ;
      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   }


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
}



}
