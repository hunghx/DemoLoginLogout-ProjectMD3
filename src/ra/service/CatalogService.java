package ra.service;

import ra.config.IOFile;
import ra.model.Catalog;


import java.util.List;

public class CatalogService implements IGenericSevice<Catalog,Integer> {
    private IOFile<Catalog> catalogData;
    private List<Catalog> listCatalog;
    public CatalogService() {
        this.catalogData = new IOFile<>();
        this.listCatalog = catalogData.readFromFile(IOFile.CATALOG_PATH);
    }
    @Override
    public List<Catalog> findAll() {
        return listCatalog;
    }

    @Override
    public void save(Catalog catalog) {
        if(findById(catalog.getId())==null){
            // thêm mới
            listCatalog.add(catalog);
        }else {
            // cập nhât
            listCatalog.set(listCatalog
                    .indexOf(findById(catalog.getId())),catalog);
        }
        // lưu sự thayddooir vào file
        catalogData.writeToFile(listCatalog,IOFile.CATALOG_PATH);
    }

    @Override
    public void delete(Integer integer) {
        listCatalog.remove(findById(integer));
        catalogData.writeToFile(listCatalog,IOFile.CATALOG_PATH);
    }

    @Override
    public Catalog findById(Integer integer) {
        for (Catalog u:listCatalog
        ) {
            if(u.getId()==integer){
                return u;
            }
        }
        return  null;
    }

    public int getNewId(){
        int max=0;
        for (Catalog u :listCatalog) {
            if(u.getId()>max){
                max= u.getId();
            }
        }
        return max+1;
    }
}
