/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import shopboots.classes.Product;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author
 */
public class ProductFacade extends AbstractFacade<Product>{
   
    private EntityManager em;
    
    public ProductFacade(Class<Product> entityClass) {
        super(entityClass);
        init();
    }

    private void init(){
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
