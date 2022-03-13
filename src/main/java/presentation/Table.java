package presentation;

import dao.AbstractDAO;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clasa pentru generarea tabelelor afisate in interfata grafica cu utilizatorul
 * @param <T> tipul obiectului corspuzator tabelei din baza de date care va aparea in interfata grafica
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 17, 2021
 */
public class Table<T> {
    /**
     * Logger
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Tipul obiectelor pentru care se va genera tabelul
     */
    private final Class<T> type;

    /**
     * Lista obiectelor care vor fi afisate in tabel
     */
    private List<T> objectList;

    /**
     * Tabelul ce va contine informatiile despre obiecte
     */
    private JTable contentTable;

    /**
     * Constructor fara parametri
     */
    @SuppressWarnings("unchecked")
    public Table() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Getter pentru tabelul ce contine informatiile
     * @return tabelul de continut
     */
    public JTable getContentTable() {
        return contentTable;
    }

    /**
     * Getter pentru determinarea id-ului obiectului selectat in tabel
     * @return id-ul obiectului selectat in tabel
     */
    public int getSelectedId() {
        int row = this.contentTable.getSelectedRow();
        String stringId = (String)this.contentTable.getValueAt(row, 0);
        return Integer.parseInt(stringId);
    }

    /**
     * Metoda pentru generarea tabelului folosind Java Reflection
     * @param objectList lista de obiecte de afisat in tabel
     */
    public void generateTable(List<T> objectList) {
        this.objectList = objectList;

        int nbOfColumns = type.getDeclaredFields().length;
        int nbOfRows = objectList.size();

        String[][] data = new String[nbOfRows][nbOfColumns];
        String[] tableHeader = new String[nbOfColumns];

        int currentColumn = 0;
        for (Field f : type.getDeclaredFields()) {
            tableHeader[currentColumn] = f.getName();
            System.out.println(f.getName());
            currentColumn++;
        }

        int objIndex = 0;
        for (T t : objectList) {
            int fieldIndex = 0;
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    data[objIndex][fieldIndex] = new String();
                    data[objIndex][fieldIndex] = f.get(t).toString();
                } catch (IllegalAccessException e) {
                    System.out.println("generateTable: " + e.getMessage());
                }
                fieldIndex++;
            }
            objIndex++;
        }

        this.contentTable = new JTable(data, tableHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return true;
            }
        };
        contentTable.setBounds(30, 40, 150, 150);
        contentTable.setRowHeight(30);
        contentTable.setBackground(new Color(135, 62, 35));
        contentTable.setFont(new Font("Arial", Font.BOLD, 14));
        contentTable.setSelectionBackground(new Color(118,181,197));
        contentTable.setForeground(new Color(238, 238, 228));
        contentTable.setEnabled(true);

        JTableHeader header = contentTable.getTableHeader();
        header.setBackground(new Color(118,181,197));
        header.setForeground(new Color(135, 62, 35));
        header.setFont(new Font("Arial", Font.BOLD, 14));

        this.contentTable.setVisible(true);
    }
}