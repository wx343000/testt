package com.zjx.client;

import com.sun.xml.internal.bind.v2.TODO;
import com.zjx.entity.ContactGroup;
import com.zjx.entity.ContactUser;
import com.zjx.service.impl.ContactGroupServiceImpl;
import com.zjx.service.impl.ContactUserServiceImpl;
import com.zjx.vo.ContactUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Contact extends JFrame {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private static ContactUserServiceImpl userService = (ContactUserServiceImpl) context.getBean("contactUserServiceImpl");
    private static ContactGroupServiceImpl groupService = (ContactGroupServiceImpl) context.getBean("contactGroupServiceImpl");
    //存储用户数据
    private static List<ContactUserVo> userArrayList = new ArrayList<ContactUserVo>();
    private static List<ContactGroup> groupArrayList = new ArrayList<ContactGroup>();
    private String[] Buttons = new String[]{"显示所有联系人[Ctrl+1]", "删除联系人[Ctrl+2]", "添加群组[Ctrl+3]", "新建联系人[Ctrl+4]", "查询联系人[Ctrl+5]", "个人信息"};
    private static JPanel bottomPanel = new JPanel();
    private JPanel parentPanel = new JPanel();
    private ListUserPanel listUserPanel;
    private CreatePanel createPanel;

//    public static void main(String[] args) {
//        userListInit();
//        Contact contact = new Contact();
//        contact.frameInit();
//    }

    //初始化窗口
    public Contact() {
        userListInit();
//        JFrame frame = new JFrame("通讯录");
        parentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        parentPanel.setLayout(new BorderLayout(0, 0));

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //显示所有联系人
        final JButton listButton = new JButton(Buttons[0]);
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userArrayList = userService.selectAllUser();
                bottomPanel.removeAll();
                listUserPanel = new ListUserPanel();
                bottomPanel.add(listUserPanel, BorderLayout.CENTER);
                bottomPanel.updateUI();
            }
        });
        listButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listButton.doClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        topPanel.add(listButton);

        //删除联系人
        final JButton delButton = new JButton(Buttons[1]);
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listUserPanel.del();
                bottomPanel.removeAll();
                listUserPanel = new ListUserPanel();
                bottomPanel.add(listUserPanel, BorderLayout.CENTER);
                bottomPanel.updateUI();
            }
        });
        delButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delButton.doClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        topPanel.add(delButton);

        //添加群组
        final JButton AddGroupButton = new JButton(Buttons[2]);
        AddGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                AddGroupPanel addGroupPanel = new AddGroupPanel();
                bottomPanel.add(addGroupPanel, BorderLayout.CENTER);
                bottomPanel.updateUI();
            }
        });
        AddGroupButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddGroupButton.doClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        topPanel.add(AddGroupButton);

        //新建联系人
        final JButton createButton = new JButton(Buttons[3]);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                createPanel = new CreatePanel();
                bottomPanel.add(createPanel, BorderLayout.CENTER);
                bottomPanel.updateUI();
            }
        });
        createButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createButton.doClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        topPanel.add(createButton);

        //查询联系人
        final JButton queryButton = new JButton(Buttons[4]);
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topPanel.removeAll();
                JLabel jLabel = new JLabel("姓名：");;
                JLabel jLabel1 = new JLabel("电话：");
                final JTextField nameTextField = new JTextField(8);
                final JTextField phoneTextField = new JTextField(8);
                JButton button = new JButton("查询");
                topPanel.add(jLabel);
                topPanel.add(nameTextField);
                topPanel.add(jLabel1);
                topPanel.add(phoneTextField);
                topPanel.add(button);
                topPanel.updateUI();
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //TODO
                        userArrayList = userService.selectUserBy(nameTextField.getText(), phoneTextField.getText());
                        listUserPanel = new ListUserPanel();
                        bottomPanel.removeAll();
                        System.out.println(userArrayList);
                        bottomPanel.add(listUserPanel, BorderLayout.CENTER);
                        bottomPanel.updateUI();
                        topPanel.removeAll();
                        topPanel.add(listButton);
                        topPanel.add(delButton);
                        topPanel.add(AddGroupButton);
                        topPanel.add(createButton);
                        topPanel.add(queryButton);
                        topPanel.updateUI();
                    }
                });
            }
        });
        queryButton.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queryButton.doClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        topPanel.add(queryButton);

//        for (int i = 0; i < Buttons.length; i++) {
//            JButton jButton = new JButton(Buttons[i]);
//            addListener(jButton);
//            topPanel.add(jButton);
//        }
        //将上方panel添加到父panel中
        parentPanel.add(topPanel, BorderLayout.NORTH);

        //将下方panel添加到父panel中
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.removeAll();
        listUserPanel = new ListUserPanel();
        bottomPanel.add(listUserPanel, BorderLayout.CENTER);
        bottomPanel.updateUI();
        parentPanel.add(bottomPanel, BorderLayout.CENTER);

        add(parentPanel);
        setVisible(false);
        setBounds(450, 200, 1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //联系人列表panel
    static class ListUserPanel extends JPanel {
        private JTable table;
        private DefaultTableModel model;// 用于存储表格数据
        private String oldValue = "";// 保存单元格编辑 前的值

        /**
         * Create the panel.
         */
        public ListUserPanel() {
            setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane();
            add(scrollPane, BorderLayout.CENTER);

            table = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            };
            //设置数据居中对齐
            DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
            cr.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, cr);

            scrollPane.setColumnHeaderView(table);
            // 初始化存储表格数据的对象
            model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"序号", "名字", "电话", "性别", "地址", "邮箱", "群组", "备注"});
            // 将数据绑定到对象中
            table.setModel(model);
            table.setRowHeight(30);

            scrollPane.setViewportView(table);

            loadData();
//             为表格绑定修改值后的事件
            model.addTableModelListener(new TableModelListener() {
                public void tableChanged(TableModelEvent e) {
                    if (e.getColumn() < 0)
                        return;
                    String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
                            .toString();
                    // 如果旧的值 和新的值一样，直接 返回
                    if (nVal.equals(oldValue)) {
                        return;
                    }
                    // 判断当前编辑的单元格是否是主键列
                    if (e.getColumn() == 0) {
                        // 还原旧的值
                        table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
                        return;
                    }
                    // 更新数据
                    int number_vo = (Integer) table.getValueAt(e.getLastRow(), 0);
                    //根据当前选择的行，遍历user列表找到对应的一个数据，更新该数据
                    for (ContactUserVo contactUserVo : userArrayList) {
                        if (contactUserVo.getNumber_vo() == number_vo) {
                            ContactUser user = new ContactUser();
                            user.setNumber(contactUserVo.getNumber());
                            user.setName(table.getValueAt(e.getLastRow(), 1).toString());
                            user.setPhone(table.getValueAt(e.getLastRow(), 2).toString());
                            user.setSex(table.getValueAt(e.getLastRow(), 3).toString());
                            user.setAddress(table.getValueAt(e.getLastRow(), 4).toString());
                            user.setEmail(table.getValueAt(e.getLastRow(), 5).toString());
                            user.setUser_group(table.getValueAt(e.getLastRow(), 6).toString());
                            user.setPs(table.getValueAt(e.getLastRow(), 7).toString());
                            userService.updateUser(user);
                            BeanUtils.copyProperties(user,contactUserVo);
                            userArrayList.set(e.getLastRow(),contactUserVo);
                            break;
                        }
                    }
                    loadData();
                }
            });
        }
        /**
         *根据userArrayList加载数据
         */
        public void loadData() {
            // 清除旧的数据
            model.getDataVector().clear();
            JComboBox cob = new JComboBox(new String[]{"男", "女"});
            ArrayList<java.lang.String> strings = new ArrayList<java.lang.String>();
            for (ContactGroup contactGroup : groupArrayList) {
                java.lang.String c_group = contactGroup.getC_group();
                strings.add(c_group);
            }
            JComboBox cobGroup = new JComboBox(strings.toArray());
            // 创建一个使用下拉框代替编辑框的单元格对象
            DefaultCellEditor sex = new DefaultCellEditor(cob);
            DefaultCellEditor group = new DefaultCellEditor(cobGroup);
            DefaultCellEditor number_vo = new DefaultCellEditor(new JTextField());

            // 获取表格的列model对象
            TableColumnModel col = table.getColumnModel();
            // 获取部门的列，设置这个列为下拉框列类型
            col.getColumn(3).setCellEditor(sex);
            col.getColumn(6).setCellEditor(group);
            // 遍历每一条数据，添加到model中
            for (ContactUserVo user : userArrayList) {
                model.addRow(new Object[]{user.getNumber_vo(), user.getName(), user.getPhone(), user.getSex(), user.getAddress(),
                        user.getEmail(), user.getUser_group(), user.getPs()});
            }
        }

        public void del() {
            if (table.getSelectedRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
                return;
            }
            int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
            // 判断用户是否点击
            if (result == JOptionPane.OK_OPTION) {
                Integer number_vo = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
                System.out.println(number_vo);
                for (ContactUserVo contactUserVo : userArrayList) {
                    if (contactUserVo.getNumber_vo() == number_vo) {
                        ContactUser contactUser = new ContactUser();
                        BeanUtils.copyProperties(contactUserVo, contactUser);
                        userService.deleteUser(contactUser);
                        break;
                    }
                }
            }
        }
    }

    //新建联系人panel
    static class CreatePanel extends JPanel {
        private JTextField number;
        private JTextField name;
        private JTextField phone;
        private JComboBox sex;
        private JTextField address;
        private JTextField email;
        private JComboBox group;
        private JTextField ps;

        /**
         * Create the panel.
         */
        public CreatePanel() {
            setLayout(null);

            final JLabel label_ps = new JLabel("");
            add(label_ps);

            JLabel label = new JLabel("id：");
            label.setBounds(430, 65, 100, 25);
            add(label);
            number = new JTextField();
            number.setBounds(480, 65, 146, 25);
            add(number);

            JLabel label_1 = new JLabel("名字：");
            label_1.setBounds(430, 95, 100, 15);
            add(label_1);
            name = new JTextField();
            name.setBounds(480, 95, 146, 25);
            add(name);

            JLabel label_2 = new JLabel("电话：");
            label_2.setBounds(430, 125, 54, 15);
            add(label_2);
            phone = new JTextField();
            phone.setBounds(480, 125, 146, 25);
            add(phone);

            JLabel label_3 = new JLabel("性别：");
            label_3.setBounds(430, 155, 54, 15);
            add(label_3);
            sex = new JComboBox(new String[]{"男", "女"});
            sex.setBounds(480, 155, 146, 25);
            add(sex);

            JLabel label_4 = new JLabel("地址：");
            label_4.setBounds(430, 185, 54, 15);
            add(label_4);
            address = new JTextField();
            address.setBounds(480, 185, 146, 25);
            add(address);

            JLabel label_5 = new JLabel("邮箱：");
            label_5.setBounds(430, 215, 54, 15);
            add(label_5);
            email = new JTextField();
            email.setBounds(480, 215, 146, 25);
            add(email);

            JLabel label_6 = new JLabel("群组：");
            label_6.setBounds(430, 245, 54, 15);
            add(label_6);
            ArrayList<String> strings = new ArrayList<String>();
            for (ContactGroup contactGroup : groupArrayList) {
                strings.add(contactGroup.getC_group());
            }
            group = new JComboBox(strings.toArray());
            group.setBounds(480, 245, 146, 25);
            add(group);
//            group = new JTextField();
//            group.setBounds(480, 245, 146, 25);
//            add(group);

            JLabel label_7 = new JLabel("备注：");
            label_7.setBounds(430, 275, 54, 15);
            add(label_7);
            ps = new JTextField();
            ps.setBounds(480, 275, 146, 25);
            add(ps);

            label_ps.setVisible(false);
            JButton btnNewButton = new JButton("新建联系人");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int numberText = Integer.parseInt(number.getText());
                    String nameText = name.getText();
                    String phoneText = phone.getText();
                    String sexText = (String) sex.getSelectedItem();
                    String addressText = address.getText();
                    String emailText = email.getText();
                    String groupText = (String) group.getSelectedItem();
                    String psText = ps.getText();
                    String pattern = "^1[3|4|5|7|8][0-9]{9}$";
                    if(Pattern.matches(pattern,phoneText)){
                        ContactUser user = new ContactUser();
                        user.setNumber(numberText);
                        user.setName(nameText);
                        user.setPhone(phoneText);
                        user.setSex(sexText);
                        user.setAddress(addressText);
                        user.setEmail(emailText);
                        user.setUser_group(groupText);
                        user.setPs(psText);
                        userService.insertUser(user);
                        userArrayList = userService.selectAllUser();
                        ListUserPanel listUserPanel = new ListUserPanel();
                        bottomPanel.removeAll();
                        bottomPanel.add(listUserPanel, BorderLayout.CENTER);
                        bottomPanel.updateUI();
                    }else {
                        System.out.println(phoneText);
                        Font font = new Font(null, Font.BOLD, 10);
                        label_ps.setBounds(450, 45, 100, 25);
                        label_ps.setFont(font);
                        label_ps.setForeground(Color.red);
                        label_ps.setVisible(true);
                        label_ps.setText("手机号不符合规范");
                    }
                }
            });
            btnNewButton.setBounds(460, 305, 116, 25);
            add(btnNewButton);
        }
    }

    //添加新群组
    static class AddGroupPanel extends JPanel {
        public AddGroupPanel() {
            setLayout(null);

            JLabel label = new JLabel("新群组：");
            label.setBounds(430, 210, 100, 25);
            add(label);
            final JTextField jTextField = new JTextField();
            jTextField.setBounds(480, 210, 146, 25);
            add(jTextField);

            JButton btnNewButton = new JButton("新建群组");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    ContactGroup contactGroup = new ContactGroup();
                    contactGroup.setC_group(jTextField.getText());
                    contactGroup.setGroup_id(UUID.randomUUID().toString());
                    groupService.insertGroup(contactGroup);
                    groupArrayList = groupService.selectAllGroup();
                    ListUserPanel listUserPanel = new ListUserPanel();
                    bottomPanel.removeAll();
                    bottomPanel.add(listUserPanel, BorderLayout.CENTER);
                    bottomPanel.updateUI();
                }
            });
            btnNewButton.setBounds(460, 250, 116, 25);
            add(btnNewButton);

        }
    }

    /**
     * 初始化数据
     */
    public static void userListInit() {
        userArrayList = userService.selectAllUser();
        groupArrayList = groupService.selectAllGroup();
    }
}
