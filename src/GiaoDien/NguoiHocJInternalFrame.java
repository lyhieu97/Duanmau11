/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import com.polypro.dao.NguoiHocDAO;
import com.polypro.helper.DateHelper;
import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import com.polypro.helper.UHelper;
import com.polypro.model.NguoiHoc;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NguoiHocJInternalFrame extends javax.swing.JInternalFrame {
    int index = 0;
    NguoiHocDAO dao = new NguoiHocDAO();

    /**
     * Creates new form NguoiHoc1JInternalFrame
     */
    public NguoiHocJInternalFrame() {
        initComponents();
        init();
    }
    void init() {
        setFrameIcon(ShareHelper.APP_ICON_1);
        tabs.setSelectedIndex(1);
    }   
    void load() {
        DefaultTableModel model = (DefaultTableModel) tbl_GridView.getModel();
        model.setRowCount(0);
        try {
            String keyword = txt_timkiem.getText();
            List<NguoiHoc> list = dao.selectByKeyword(keyword);
            for (NguoiHoc nh : list) {
                Object[] row = {
                    nh.getMaNH(),
                    nh.getHoTen(),
                    nh.isGioiTinh() ? "Nam" : "Nữ",
                    DateHelper.toString(nh.getNgaySinh()),
                    nh.getDienThoai(),
                    nh.getEmail(),
                    nh.getMaNV(),
                    DateHelper.toString(nh.getNgayDK())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        NguoiHoc model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi " + e);
        }
    }

    void update() {
        NguoiHoc model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String manh = txt_MaNh.getText();
            try {
                dao.delete(manh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (HeadlessException e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        NguoiHoc model = new NguoiHoc();
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayDK(DateHelper.now());
        this.setModel(model);
    }

    void edit() {
        try {
            String manh = (String) tbl_GridView.getValueAt(this.index, 0);
            NguoiHoc model = dao.findById(manh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(NguoiHoc model) {
        txt_MaNh.setText(model.getMaNH());
        txt_hoten.setText(model.getHoTen());
        cbx_gioitinh.setSelectedIndex(model.isGioiTinh() ? 0 : 1);
        txt_ngaysinh.setText(DateHelper.toString(model.getNgaySinh()));
        txt_dienthoai.setText(model.getDienThoai());
        txt_email.setText(model.getEmail());
        txt_noidung.setText(model.getGhiChu());
    }

    NguoiHoc getModel() {
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(txt_MaNh.getText());
        model.setHoTen(txt_hoten.getText());
        model.setGioiTinh(cbx_gioitinh.getSelectedIndex() == 0);
        model.setNgaySinh(DateHelper.toDate(txt_ngaysinh.getText()));
        model.setDienThoai(txt_dienthoai.getText());
        model.setEmail(txt_email.getText());
        model.setGhiChu(txt_noidung.getText());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayDK(DateHelper.now());
        return model;
    }

    void setStatus(boolean insertable) {
        txt_MaNh.setEditable(insertable);
        btn_insert.setEnabled(insertable);
        btn_update.setEnabled(!insertable);
        btn_delete.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tbl_GridView.getRowCount() - 1;
        btn_first.setEnabled(!insertable && first);
        btn_prev.setEnabled(!insertable && first);
        btn_last.setEnabled(!insertable && last);
        btn_next.setEnabled(!insertable && last);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_title = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnl_edit = new javax.swing.JPanel();
        lbl_MaNh = new javax.swing.JLabel();
        txt_MaNh = new javax.swing.JTextField();
        lbl_hoten = new javax.swing.JLabel();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        txt_hoten = new javax.swing.JTextField();
        lbl_gioitinh = new javax.swing.JLabel();
        cbx_gioitinh = new javax.swing.JComboBox();
        lbl_ngaysinh = new javax.swing.JLabel();
        txt_ngaysinh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_dienthoai = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        lbl_ghichu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_noidung = new javax.swing.JTextArea();
        pnl_list = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_GridView = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        lbl_timkiem = new javax.swing.JLabel();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(0, 0, 204));
        lbl_title.setText("QUẢN LÝ NGƯỜI HỌC");

        lbl_MaNh.setText("Mã người học");

        lbl_hoten.setText("Họ và tên");

        btn_insert.setText("Thêm");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setText("Sửa");
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("Xóa");
        btn_delete.setEnabled(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_clear.setText("Mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/66615753_1056307041245136_3788192370894307328_n.png"))); // NOI18N
        btn_last.setEnabled(false);
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/66187495_2636402163253001_1471257866336731136_n.png"))); // NOI18N
        btn_next.setEnabled(false);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/66594471_389332521931713_4328421582247559168_n.png"))); // NOI18N
        btn_prev.setEnabled(false);
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/66335151_503799670374146_4836948570542178304_n.png"))); // NOI18N
        btn_first.setEnabled(false);
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        lbl_gioitinh.setText("Giới tính");

        cbx_gioitinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));

        lbl_ngaysinh.setText("Ngày sinh");

        jLabel1.setText("Điện thoại");

        jLabel2.setText("Địa chỉ email");

        lbl_ghichu.setText("Ghi chú");

        txt_noidung.setColumns(20);
        txt_noidung.setRows(5);
        jScrollPane2.setViewportView(txt_noidung);

        javax.swing.GroupLayout pnl_editLayout = new javax.swing.GroupLayout(pnl_edit);
        pnl_edit.setLayout(pnl_editLayout);
        pnl_editLayout.setHorizontalGroup(
            pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(txt_MaNh)
                    .addComponent(txt_hoten)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_editLayout.createSequentialGroup()
                        .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_dienthoai, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_MaNh)
                                    .addComponent(lbl_hoten)
                                    .addComponent(lbl_gioitinh)
                                    .addComponent(jLabel1)))
                            .addComponent(lbl_ghichu)
                            .addGroup(pnl_editLayout.createSequentialGroup()
                                .addComponent(btn_insert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_clear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_editLayout.createSequentialGroup()
                                .addComponent(btn_first)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_prev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_last))
                            .addComponent(jLabel2)
                            .addComponent(lbl_ngaysinh)
                            .addComponent(txt_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txt_email))))
                .addContainerGap())
        );
        pnl_editLayout.setVerticalGroup(
            pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_editLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_MaNh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_hoten)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_gioitinh)
                    .addComponent(lbl_ngaysinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_ghichu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_first, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_prev, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_next)
                    .addComponent(btn_last)
                    .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_insert)
                        .addComponent(btn_update)
                        .addComponent(btn_delete)
                        .addComponent(btn_clear)))
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", pnl_edit);

        tbl_GridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI", "EMAIL", "MÃ NV", "NGÀY ĐK"
            }
        ));
        tbl_GridView.setRowHeight(25);
        tbl_GridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_GridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_GridView);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_timkiem.setText("Tìm");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timkiem)
                .addGap(18, 18, 18)
                .addComponent(btn_timkiem)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiem))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        lbl_timkiem.setText("TÌM KIẾM");

        javax.swing.GroupLayout pnl_listLayout = new javax.swing.GroupLayout(pnl_list);
        pnl_list.setLayout(pnl_listLayout);
        pnl_listLayout.setHorizontalGroup(
            pnl_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl_listLayout.createSequentialGroup()
                        .addComponent(lbl_timkiem)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_listLayout.setVerticalGroup(
            pnl_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl_timkiem)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", pnl_list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_title)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        if(!UHelper.checkNull_JTextField(txt_MaNh, "Mã NH ")){
            return;
        }
        if(!UHelper.checkTrungMaNH_JTextField(txt_MaNh)){
            return;
        }
        if(!UHelper.checkSize_MaNH(txt_MaNh)){
            return;
        }
        if(!UHelper.checkNull_JTextField(txt_hoten, "Họ tên ")){
            return;
        }
        if(!UHelper.checkHoTen_JTextField(txt_hoten)){
            return;
        }
        if(!UHelper.checkNull_JCombobox(cbx_gioitinh)){
            return;
        }
        if(!UHelper.checkNull_JTextField(txt_ngaysinh, "Ngày sinh ")){
            return;
        }
        if(!UHelper.checkDate_JTextField(txt_ngaysinh, "Ngày Sinh")){
            return;
        }
        if(!UHelper.checkNull_JTextField(txt_dienthoai, "Điện thoại ")){
            return;
        }
        if(!UHelper.checkDienThoai_JTextField(txt_dienthoai)){
            return;
        }
        if(!UHelper.checkNull_JTextField(txt_email, "Email ")){
            return;
        }
        if(!UHelper.checkEmail_JTextField(txt_email)){
            return;
        }
        if(!UHelper.checkNull_JTextArea(txt_noidung, "Ghi chú ")){
            return;
        }
        
        
        
        
        
        
        this.insert();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        // TODO add your handling code here:
        this.index = tbl_GridView.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btn_firstActionPerformed

    private void tbl_GridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GridViewMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbl_GridView.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbl_GridViewMouseClicked

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        if(!UHelper.checkNull_JTextField(txt_timkiem, "Tìm kiếm")){
            return;
        }
        this.load();
        this.clear();
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox cbx_gioitinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_MaNh;
    private javax.swing.JLabel lbl_ghichu;
    private javax.swing.JLabel lbl_gioitinh;
    private javax.swing.JLabel lbl_hoten;
    private javax.swing.JLabel lbl_ngaysinh;
    private javax.swing.JLabel lbl_timkiem;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel pnl_edit;
    private javax.swing.JPanel pnl_list;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbl_GridView;
    private javax.swing.JTextField txt_MaNh;
    private javax.swing.JTextField txt_dienthoai;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JTextArea txt_noidung;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
