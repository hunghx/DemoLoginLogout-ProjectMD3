package ra.controller;

import ra.config.IOFile;
import ra.model.Catalog;
import ra.service.CatalogService;

import java.util.List;

public class CatalogController {
    private CatalogService catalogService;
    public CatalogController(){
        catalogService= new CatalogService();
    }
    public List<Catalog> findAll() {
     return catalogService.findAll();
    }


    public void save(Catalog catalog) {
        catalogService.save(catalog);
    }


    public void delete(Integer integer) {
        catalogService.delete(integer);
    }


    public Catalog findById(Integer integer) {
      return catalogService.findById(integer);
    }

    public int getNewId(){
      return catalogService.getNewId();
    }
}
