/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas.general;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanperez
 */
public class MyTableModel extends DefaultTableModel {
    
        @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class clazz = String.class;
      switch (columnIndex) {
        case 1:
          clazz = Boolean.class;
          break;
        default:
          clazz = String.class;
          break;
      }
      return clazz;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 1;
    }
}
