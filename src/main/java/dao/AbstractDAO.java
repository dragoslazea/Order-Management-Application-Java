package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * Clasa pentru operatiile pe baza de date - s-au folosit tipuri generice pentru a facilita utilizarea acestor operatii pentru toate cele trei tipuri de obiecte: Client, Product, Order
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */

public class AbstractDAO<T> {
    /**
     * Variabila statica LOGGER
     * */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Tipul Obiectului asupra caruia se vor efectua operatiile
     * */
    private final Class<T> type;

    /**
     * Constructor in care se determina tipul obiectului
     * */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda care creeaza o interogare de tip SELECT
     * @param field field-ul după care se face selectia
     * @return interogarea de tip select sub forma de String
     * */
    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda care creeaza o interogare de tip SELECT ALL
     * @return interogarea de tip select all sub forma de String
     * */
    public String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Metoda care creeaza o interogare de tip INSERT
     * @param fields vectorul de field-uri care va fi inserat în tabela corespunzătoare tipului de obiect
     * @return interogarea de tip insert sub forma de String
     * */
    private String createInsertQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ordersdb.");
        sb.append(type.getSimpleName());
        int currentParameter = 1;
        for (Field f: fields) {
            if (f.getName().equals("id") == false) {
                if (currentParameter == 1) {
                    sb.append("(" + f.getName());
                } else {
                    sb.append(", " + f.getName());
                }
                currentParameter++;
            }
        }
        sb.append(") VALUES ");
        for (int i = 1; i <= fields.length - 1; i++) {
            if (i == 1) {
                sb.append("(?");
            }
            else {
                sb.append(", ?");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Metoda pentru crearea unei interigari care determina id-ul maxim din tabela corespunzatoare tipului de obiect
     * @return interogarea sub forma de String
     */
    public String createGetMaxIdQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id IN (SELECT MAX(id) FROM ");
        sb.append(type.getSimpleName());
        sb.append(")");
        return sb.toString();
    }

    /**
     * Meotda pentru crearea unei interogari de tip update
     * @param fields vectorul de campuri care vor fi actualizate
     * @return interogarea sub forma de string
     */
    public String createUpdateQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ordersdb.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int currentParameter = 1;
        for (Field f:
             fields) {
            if (!f.getName().equals("id")) {
                if (currentParameter != 1) {
                    sb.append(", ");
                }
                sb.append(f.getName());
                sb.append(" = ?");
                currentParameter++;
            }
        }
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    /**
     * Meotda pentru crearea unei interogari de tip delete
     * @param field campul după care se face stergerea
     * @return interogarea sub forma de string
     */
    public String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda pentru determinarea id-ului maxim din tabela cu tipul obiectului
     * @param t obiectul de tip T
     * @return valoarea maxima a id-ului din tabela corespunzatoare tipului de obiect
     */
    public int getMaxId(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createGetMaxIdQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            int maxId = resultSet.getInt(1);
            return maxId;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getMaxId " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Metoda care returneaza o lista cu toate inregistrarile din tabela corespunzatoare tipului T din baza de date
     * @return lista cu toate obiectele de tip T
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda pentru determinarea obiectului de tip T care are id-ul transmis ca parametru
     * @param id id-ul obiectului de tip T cautat
     * @return obiectul de tip T care are id-ul id
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda pentru inserarea unui obiect de tip T in tabela corespunzatoare tipului
     * @param t obiectul de inserat
     * @return obiectul inserat in caz de succes, null altfel
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t.getClass().getDeclaredFields());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int currentParameter = 1;
            for (Field f: t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id"))
                    continue;
                if (f.getType().getSimpleName().equals("String")) {
                    statement.setString(currentParameter, (String)f.get(t));
                } else if (f.getType().getSimpleName().equals("int")) {
                    statement.setInt(currentParameter, (int)f.get(t));
                } else if (f.getType().getSimpleName().equals("float")) {
                    statement.setFloat(currentParameter, (float)f.get(t));
                }
                currentParameter += 1;
            }
            statement.execute();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() +  "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care creeaza o lista de obiecte de tip T
     * @param resultSet obiect de tip ResultSet
     * @return lista de obiecte create
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda pentru actualizarea campurilor unui obiect de tip T in tabela corespunzatoare tipului
     * @param t obiectul de actualizat
     * @return obiectul actualizat în caz de succes, null altfel
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t.getClass().getDeclaredFields());
        int idForUpdate = -1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int currentParameter = 1;
            for (Field f: t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getName().equals("id")) {
                    idForUpdate = (int)f.get(t);
                    continue;
                }
                if (f.getType().getSimpleName().equals("String")) {
                    statement.setString(currentParameter, (String)f.get(t));
                } else if (f.getType().getSimpleName().equals("int")) {
                    statement.setInt(currentParameter, (int)f.get(t));
                } else if (f.getType().getSimpleName().equals("float")) {
                    statement.setFloat(currentParameter, (float)f.get(t));
                }
                currentParameter += 1;
            }
            statement.setInt(currentParameter, idForUpdate);
            statement.execute();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() +  "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda pentru stergerea unei inregistrari din tabela corespunzatoare tipului de obiect T
     * @param id id-ul obiectului de sters
     */
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
