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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.opencuas;

import com.quinsoft.zeidon.*;
import com.quinsoft.zeidon.vml.*;

/**
 * @author DG
 *
 */
public class mFASrc extends VmlObjectOperations
{

    /**
     * @param task
     */
    public mFASrc(View view)
    {
        super( view );
    }

    public int omFASrc_dTotalNAcceptedByYear( View mFASrc, 
                                              String InternalEntityStructure, 
                                              String InternalAttribStructure, 
                                              Integer getOrSet )
    {
        zVIEW  mYearLST = new zVIEW();
        int RESULT;
        int nZRetCode;
        Double dAmount = 1.23;
        Double dRejected = 0.0;
        Double dTempDecimal_0 = 0.0;
        Double dTempDecimal_1 = 0.0;
        
        RESULT = GetViewByName( mYearLST, "mYearLST", mFASrc, zLEVEL_TASK );

        switch ( getOrSet )
        {
            case zDERIVED_GET:
//                RESULT = SetCursorFirstEntity( mFASrc, "FinAidAwardAssigned", "" );
//                while ( RESULT > zCURSOR_UNCHANGED )
//                {
//                   if ( CompareAttributeToAttribute( mFASrc, "AwardedCollegeYear",
//                      "ID", mYearLST, "CollegeYear", "ID" ) == 0 )
//                   {
//                       
//                       if ( CompareAttributeToString( mFASrc, "FinAidAwardAssigned",
//                                                      "Amount", "" ) != 0 ||
//                            CompareAttributeToString( mFASrc, "FinAidAwardAssigned",
//                                                      "AwardStatus", "N" ) == 0 )
//                       {
//                          //:dRejected = mFASrc.FinAidAwardAssigned.AmountOffered - mFASrc.FinAidAwardAssigned.Amount   
//                          dTempDecimal_0 = GetDecimalFromAttribute( dTempDecimal_0, mFASrc,
//                                                                   "FinAidAwardAssigned", "AmountOffered" );
//                          dTempDecimal_1 = GetDecimalFromAttribute( dTempDecimal_1, mFASrc,
//                                                                    "FinAidAwardAssigned", "Amount" );
//                          dRejected = dTempDecimal_0 - dTempDecimal_1;
//                       }
//                        
//                       dAmount = dAmount + dRejected;
//                       dRejected = 0.0;
//                   }
//                   RESULT = SetCursorNextEntity( mFASrc, "FinAidAwardAssigned", "" );
//                }

                nZRetCode = StoreValueInRecord( mFASrc, InternalEntityStructure,
                                                InternalAttribStructure, dAmount, 0 );
                break;
                
            case zDERIVED_SET:
                break;
        }
        return 0;
    }
}
