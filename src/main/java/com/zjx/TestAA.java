package com.zjx;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestAA {
    private static List<Order> orders = new ArrayList<Order>();
    private static DefaultTableColumnModel tableColumnModel = new DefaultTableColumnModel();
    private static TableColumn colState = new TableColumn();
    private static TableColumn colState1 = new TableColumn();
    private static TableColumn colState2 = new TableColumn();

    public static void main(String[] args) {
        Order order = new Order("a", "b", "c");
        orders.add(order);
        orders.add(order);
        orders.add(order);

        colState.setHeaderValue("head1");
        colState.setModelIndex(0);

        colState1.setHeaderValue("head2");
        colState1.setModelIndex(1);

        colState2.setHeaderValue("head3");
        colState2.setModelIndex(2);

        tableColumnModel.addColumn(colState);
        tableColumnModel.addColumn(colState1);
        tableColumnModel.addColumn(colState2);

        JTable jTable = new JTable(new OrderListTableModel(), tableColumnModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JFrame frame = new JFrame();
        frame.add(jScrollPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

    }

    private static class OrderListTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;


        public int getRowCount() {
            return orders == null ? 0 : orders.size();
        }

        public int getColumnCount() {
            return tableColumnModel.getColumnCount();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int row, int columnIndex) {
            if (row < 0 || row >= orders.size()) {
                return null;
            }

            Order order = orders.get(row);

            if (columnIndex == colState.getModelIndex()) {
                return order.getA();
            } else if (columnIndex == colState1.getModelIndex()) {
                return order.getB();
            } else if (columnIndex == colState2.getModelIndex()) {
                return order.getC();
            } else {
                return null;
            }
        }

        private int getColumnModelIndex(TableColumn tableColumn) {
            if (tableColumn == null) {
                return -999;
            }
            return tableColumn.getModelIndex();
        }
    }
}
