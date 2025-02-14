package model.factories;

public abstract class AlquilableFactory {

    public IAlquilable iAlquilable(){
      IAlquilable alquilable = crearAlquilable();

      return alquilable;
    }

    public abstract IAlquilable crearAlquilable();
}
