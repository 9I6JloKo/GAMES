/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;
import shopboots.classes.Manager;
import tools.Singleton;

/**
 *
 * @author
 */
public class ManagerFacade extends AbstractFacade<Manager>{
   
    private EntityManager em;
    
    public ManagerFacade(Class<Manager> entityClass) {
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
