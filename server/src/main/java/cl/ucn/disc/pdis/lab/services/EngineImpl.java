/*
 * MIT License
 *
 * Copyright (c) 2020 Jorge Ignacio Pizarro Tapia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdis.lab.services;

import cl.ucn.disc.pdis.lab.zeroice.model.Engine;
import com.zeroc.Ice.Current;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The implementation of {@link cl.ucn.disc.pdis.lab.zeroice.model.Engine}.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class EngineImpl implements Engine {

    /**This method return the actual Date.
     *
     * @see Engine#getDate(Current)
     */
    @Override
    public String getDate(Current current) {

        return ZonedDateTime
                .now()
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**This method Return a boolean that certifies a rut well written.
     *
     * @param rut Check if the RUT received is correct.
     * @param current The Current object for the invocation.
     * @return
     *
     * NOTE: This part of the work was made with Eduardo Alvarez's help.
     */
    @Override
    public boolean getDigitoVerificador(String rut,Current current){

        try {
            //Clear characters "." and "-"
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");

            //Extract rut as integer from rut received to rutAux. Except DV char.
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            //Extract DV char from rut received
            char dv = rut.charAt(rut.length() - 1);

            //Verification params
            int x = 0;
            int y = 1;

            //Verification logic
            for (; rutAux != 0; rutAux /= 10) {
                y = (y + rutAux % 10 * (9 - x++ % 6)) % 11;
            }
            if (dv == (char) (y != 0 ? y + 47 : 75)) {

                return true;
            }
        } catch (java.lang.NumberFormatException e) {


        } catch (Exception e) {
            //TODO: DEBUG O RETURN SOME LIKE ERROR 001: INVALID RUT DATA.
            /*The actual problem is that this method return bool.
            * So, we only have 2 output. Maybe if I put another output type
            * I could set different output, including exception.
            */
            return false;
        }
        return false;
    }
}
