/*
 * Created by JFormDesigner on Thu Dec 19 12:51:04 CST 2019
 */

package competition.management.system.view.member;

import competition.management.system.dao.ProductionDao;
import competition.management.system.dao.TeamDao;
import competition.management.system.entity.Member;
import competition.management.system.entity.Production;
import competition.management.system.entity.Team;
import competition.management.system.util.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author zzc
 */
public class ProductInfoFrame extends JFrame {
    private Member member;
    private Team team;
    private Production product;
    private MemberMainFrame memberMainFrame;

    private final ProductionDao productionDao = JDBCUtil.getProductionDao();
    private final TeamDao teamDao = JDBCUtil.getTeamDao();

    public ProductInfoFrame(MemberMainFrame memberMainFrame) {
        this.setMemberMainFrame(memberMainFrame);
        initComponents();
    }

    public void load() {
        this.textField_id.setText(product.getId());
        this.textField_code.setText(product.getCode());
        this.textField_name.setText(product.getName());
        this.textField_path.setText(product.getPath());
        this.textArea_description.setText(product.getDescription());
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        this.team = member.getTeam();
    }

    public Production getProduct() {
        return product;
    }

    public void setProduct(Production product) {
        this.product = product;
    }

    public MemberMainFrame getMemberMainFrame() {
        return memberMainFrame;
    }

    public void setMemberMainFrame(MemberMainFrame memberMainFrame) {
        this.memberMainFrame = memberMainFrame;
    }

    private void thisWindowClosing(WindowEvent e) {
        if (this.memberMainFrame != null) {
            this.memberMainFrame.load();
            this.memberMainFrame.setVisible(true);
        }
    }

    private void buttonUpdateActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.ProductInfoFrame");
        if (member.getLeader() == false) {
            JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.not_leader"), "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        product.setId(textField_id.getText());
        product.setCode(textField_code.getText());
        product.setName(textField_name.getText());
        product.setPath(textField_path.getText());
        product.setDescription(textArea_description.getText());

        try {
            boolean result;
            if (textField_id.getText() == null || "".equals(textField_id.getText())) {
                textField_id.setText(UUID.randomUUID().toString());
                product.setId(textField_id.getText());
                result = productionDao.insert(product);
                member.getTeam().setProduction(product);
                result = result && teamDao.update(member.getTeam());
            } else {
                result = productionDao.update(product);
            }

            if (result == true) {
                JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.success"), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void button_deleteActionPerformed(ActionEvent e) {
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.ProductInfoFrame");
        try {
            boolean res = false;
            team.setProduction(new Production());
            res = teamDao.update(team) && productionDao.remove(product.getId());
            if (res == true) {
                product = team.getProduction();
                load();
                JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.success"), "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, bundle.getString("ProductInfoFrame.fail"), "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("competition.management.system.view.member.ProductInfoFrame");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        textField_id = new JTextField();
        textField_code = new JTextField();
        textField_name = new JTextField();
        textField_path = new JTextField();
        button_update = new JButton();
        scrollPane1 = new JScrollPane();
        textArea_description = new JTextArea();
        button_delete = new JButton();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("ProductInfoFrame.label1.text"));
        contentPane.add(label1);
        label1.setBounds(85, 30, 20, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText(bundle.getString("ProductInfoFrame.label2.text"));
        contentPane.add(label2);
        label2.setBounds(80, 65, 45, label2.getPreferredSize().height);

        //---- label3 ----
        label3.setText(bundle.getString("ProductInfoFrame.label3.text"));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(80, 105), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText(bundle.getString("ProductInfoFrame.label4.text"));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(80, 140), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText(bundle.getString("ProductInfoFrame.label5.text"));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(80, 185), label5.getPreferredSize()));

        //---- textField_id ----
        textField_id.setEditable(false);
        contentPane.add(textField_id);
        textField_id.setBounds(145, 25, 160, textField_id.getPreferredSize().height);
        contentPane.add(textField_code);
        textField_code.setBounds(145, 60, 160, 30);
        contentPane.add(textField_name);
        textField_name.setBounds(145, 100, 160, 30);
        contentPane.add(textField_path);
        textField_path.setBounds(145, 135, 160, 30);

        //---- button_update ----
        button_update.setText(bundle.getString("ProductInfoFrame.button_update.text"));
        button_update.addActionListener(e -> buttonUpdateActionPerformed(e));
        contentPane.add(button_update);
        button_update.setBounds(new Rectangle(new Point(90, 315), button_update.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea_description);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(150, 185, 150, 120);

        //---- button_delete ----
        button_delete.setText(bundle.getString("ProductInfoFrame.button_delete.text"));
        button_delete.addActionListener(e -> button_deleteActionPerformed(e));
        contentPane.add(button_delete);
        button_delete.setBounds(new Rectangle(new Point(255, 315), button_delete.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(400, 405);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField_id;
    private JTextField textField_code;
    private JTextField textField_name;
    private JTextField textField_path;
    private JButton button_update;
    private JScrollPane scrollPane1;
    private JTextArea textArea_description;
    private JButton button_delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
