package br.dbserver.project.stubs;

import br.dbserver.project.model.City;

public interface CityStub {
    static City cityDefault(){
        return new City(1L, "Lajeado");
    }
}
