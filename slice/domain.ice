// Custom package mapping
["java:package:cl.ucn.disc.pdis.lab.zeroice"]
module model
{
    // The API
    interface Engine
    {
        string getDate();
        /**
        * Rut Verification. User can use char like "." y "-".
        * @return boolean:
        *       TRUE =  El rut es correcto.
        *       FALSE=  El rut es incorrecto.
        */
        bool getDigitoVerificador(string rut);
    }

}
