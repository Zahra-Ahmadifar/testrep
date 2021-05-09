package model.service;

import model.entity.CategoryElement;
import model.repository.CategoryElementDA;

/**
 * Created by Asus on 5/2/2021.
 */
public class CategoryElementService {
    private static CategoryElementService categoryElementService = new CategoryElementService();

    public static CategoryElementService getInstance() {
        return categoryElementService;
    }

    private CategoryElementService() {
    }
    public CategoryElement findByCodeCategory(String code){
        CategoryElementDA categoryElementDA=new CategoryElementDA();
        return categoryElementDA.findByCode(code);
    }
}
