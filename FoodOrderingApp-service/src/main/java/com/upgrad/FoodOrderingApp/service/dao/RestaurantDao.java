package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.RestaurantCategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//This Class represents the data access object of Restaurant entity
@Repository
public class RestaurantDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to get all Restaurants as List.
     * @return List<RestaurantEntity>
     * @Catch Exception NoResultException
     */
    public List<RestaurantEntity> getAllRestaurants() {
        try {
            return entityManager.createNamedQuery("getAllRestaurants", RestaurantEntity.class).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
    //Method to get restaurant by UUID from db, returns null on error
    public RestaurantEntity getRestaurantByUuid(String uuid) {
        try {
            RestaurantEntity restaurantEntity = entityManager.createNamedQuery("getRestaurantByUUId",RestaurantEntity.class).setParameter("uuid",uuid).getSingleResult();
            return restaurantEntity;
        }catch (NoResultException nre){
            return null;
        }

    }
    // Method to update Restaurant Details
    public void updateRestaurantDetails(final RestaurantEntity updatedRestaurantEntity) {
        entityManager.merge(updatedRestaurantEntity);
    }
    //To get the list of restaurant by name from db, returns null on error
    public List<RestaurantEntity> getRestaurantsByName(String restaurantName) {
        try {
            String restaurantNameLCase = "%"+restaurantName.toLowerCase()+"%"; // to make a check with lower
            List<RestaurantEntity> restaurantEntities = entityManager.createNamedQuery("getRestaurantByName", RestaurantEntity.class).setParameter("restaurantName",restaurantNameLCase).getResultList();
            return restaurantEntities;
        }catch (NoResultException nre){
            return null;
        }

    }
    //To get the list of restaurant by Category Uuid from db, returns null on error
    public List<RestaurantCategoryEntity> getRestaurantByCategoryId(final Integer categoryID) {
        try {
            return entityManager.createNamedQuery("getRestaurantsByCategoryId", RestaurantCategoryEntity.class).setParameter("id",categoryID).getResultList();
        } catch(NoResultException nre) {
            return null;
        }
    }

}
