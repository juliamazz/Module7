package org.example.domain;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.domain.TodoItem;
import org.hibernate.type.LongType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoItemDAO {

    public static List<String> extractQueries(String input) {
        List<String> queries = new ArrayList<>();

        // Regular expression pattern to match SQL statements separated by semicolons
        Pattern pattern = Pattern.compile("(?i)\\b(INSERT\\s+INTO|DELETE\\s+FROM|UPDATE|SELECT)\\b.*?;");
        Matcher matcher = pattern.matcher(input);

        // Iterate through matches and add them to the list
        while (matcher.find()) {
            queries.add(matcher.group().trim());
        }

        return queries;
    }

    /**
     * Add Item
     * @param item
     */
    public void addItem(TodoItem item) {
        Transaction transaction = null;
        int max_id = -1;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            item.setId(max_id);

            System.out.println(max_id);
            System.out.println(item.getDescription());


            String description = item.getDescription();

            // save the object
            //session.save(item);
            String hql = String.format("INSERT INTO TodoItem (description) VALUES ('%s')",
                    description);

            List<String> queries = extractQueries(hql);

            String tmp_query = null;

            for (String query : queries) {

                System.out.println(query);
                int rowsAffected = session.createNativeQuery(query).executeUpdate();
            }




            // commit transaction
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
    }


    /**
     * Delete Item
     * @param id
     */
    public void deleteItem(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            if (!transaction.isActive()){
                transaction.begin();
            }

            // Delete ietm object
            TodoItem item = session.get(TodoItem.class, id);
            if (item != null) {
                session.delete(item);
                System.out.println("item is deleted");
            }
            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    /**
     * Get Item By ID
     * @param id
     * @return
     */
    public static TodoItem getItem(int id) {

        Transaction transaction = null;
        TodoItem todoitem = new TodoItem();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            if (!transaction.isActive()){
                transaction.begin();
            }
            // get an item object
            todoitem = session.get(TodoItem.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println("2" + todoitem.getDescription());
        return todoitem;
    }

    /**
     * Get all Users
     * @return
     */
    public List< TodoItem > getAllItems() {

        List < TodoItem > listOfItems = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            // beging transaction
            session.beginTransaction();

            // Get objects
            List<Object[]> listOfObjects = session.createNativeQuery("SELECT * FROM todoitem" ).getResultList();

            // MAP STUFF HERE
            for(Object[] object : listOfObjects) {
                int id = (int) object[0];
                String description = (String) object[1];
                TodoItem newItem = new TodoItem(id, description);
                listOfItems.add(newItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfItems;
    }
}
