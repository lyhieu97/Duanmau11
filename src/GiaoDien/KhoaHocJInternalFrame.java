/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import com.polypro.dao.ChuyenDeDAO;
import com.polypro.dao.KhoaHocDAO;
import com.polypro.helper.DateHelper;
import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import com.polypro.helper.UHelper;
import com.polypro.model.ChuyenDe;
import com.polypro.model.KhoaHoc;
import java.awt.HeadlessException;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhoaHocJInternalFrame extends javax.swing.JInternalFrame {

    int index = 0;
    KhoaHocDAO dao = new KhoaHocDAO();
    ChuyenDeDAO cddao = new ChuyenDeDAO();

    /**
     * Creates new form KhoaHocJInternalFrame
     */
    public KhoaHocJInternalFrame() {
        initComponents();
        init();
    }

    void init() {
        setFrameIcon(ShareHelper.APP_ICON_1);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tbl_GridView.getModel();
        model.setRowCount(0);
        try {
            List<KhoaHoc> list = dao.select();
            for (KhoaHoc kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getMaCD(),
                    kh.getThoiLuong(),
                    kh.getHocPhi(),
                    DateHelper.toString(kh.getNgayKG()),
                    kh.getMaNV(),
                    DateHelper.toString(kh.getNgayTao())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        KhoaHoc model = getModel();
        model.setNgayTao(new Date());
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (HeadlessException e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        KhoaHoc model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }

    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khóa học này?")) {
            Integer makh = Integer.valueOf(cbx_chuyende.getToolTipText());
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cbx_chuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayKG(DateHelper.add(30));
        model.setNgayTao(DateHelper.now());

        this.setModel(model);
    }

    void edit() {
        try {
            Integer makh = (Integer) tbl_GridView.getValueAt(this.index, 0);
            KhoaHoc model = dao.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    void setModel(KhoaHoc model) {
        cbx_chuyende.setToolTipText(String.valueOf(model.getMaKH()));
        cbx_chuyende.getModel().setSelectedItem(cddao.findById(model.getMaCD()));
        txt_ngaykg.setText(DateHelper.toString(model.getNgayKG()));
        txt_hocphi.setText(String.valueOf(model.getHocPhi()));
        txt_gio.setText(String.valueOf(model.getThoiLuong()));
        txt_ngaytao.setText(DateHelper.toString(model.getNgayTao()));
        txt_motacd.setText(model.getGhiChu());
    }

    KhoaHoc getModel() {
        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cbx_chuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());
        model.setNgayKG(DateHelper.toDate(txt_ngaykg.getText()));
        model.setHocPhi(Double.valueOf(txt_hocphi.getText()));
        model.setThoiLuong(Integer.valueOf(txt_gio.getText()));
        model.setGhiChu(txt_motacd.getText());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayTao(DateHelper.toDate(txt_ngaytao.getText()));
        model.setMaKH(Integer.valueOf(cbx_chuyende.getToolTipText()));

        return model;
    }

    void setStatus(boolean insertable) {
        btn_insert.setEnabled(insertable);
        btn_update.setEnabled(!insertable);
        btn_delete.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tbl_GridView.getRowCount() - 1;
        btn_first.setEnabled(!insertable && first);
        btn_prev.setEnabled(!insertable && first);
        btn_last.setEnabled(!insertable && last);
        btn_next.setEnabled(!insertable && last);

        btn_hocvien.setVisible(!insertable);
    }

    void selectComboBox() {
        ChuyenDe chuyenDe = (ChuyenDe) cbx_chuyende.getSelectedItem();
        txt_gio.setText(String.valueOf(chuyenDe.getThoiLuong()));
        txt_hocphi.setText(String.valueOf(chuyenDe.getHocPhi()));
    }

    void openHocVien() {
        Integer id = Integer.valueOf(cbx_chuyende.getToolTipText());
        new HocVienJFrame().setVisible(true);
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbx_chuyende.getModel();
        model.removeAllElements();
        try {
            List<ChuyenDe> list = cddao.select();
            for (ChuyenDe cd : list) {
                model.addElement(cd);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
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
        pnl_list = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_GridView = new javax.swing.JTable();
        pnl_edit = new javax.swing.JPanel();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        lbl_motacd = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_motacd = new javax.swing.JTextArea();
        lbl_chuyende = new javax.swing.JLabel();
        cbx_chuyende = new javax.swing.JComboBox();
        lbl_ngaykg = new javax.swing.JLabel();
        txt_ngaykg = new javax.swing.JTextField();
        lbl_hocphi = new javax.swing.JLabel();
        lbl_gio = new javax.swing.JLabel();
        txt_hocphi = new javax.swing.JTextField();
        txt_gio = new javax.swing.JTextField();
        lbl_nguoitao = new javax.swing.JLabel();
        lbl_ngaytao = new javax.swing.JLabel();
        txt_nguoitao = new javax.swing.JTextField();
        txt_ngaytao = new javax.swing.JTextField();
        btn_hocvien = new javax.swing.JButton();

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
        lbl_title.setText("QUẢN LÝ KHÓA HỌC");

        tbl_GridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ KH", "CHUYÊN ĐỀ", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_GridView.setRowHeight(25);
        tbl_GridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_GridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_GridView);

        javax.swing.GroupLayout pnl_listLayout = new javax.swing.GroupLayout(pnl_list);
        pnl_list.setLayout(pnl_listLayout);
        pnl_listLayout.setHorizontalGroup(
            pnl_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_listLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_listLayout.setVerticalGroup(
            pnl_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_listLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", pnl_list);

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

        lbl_motacd.setText("Ghi chú");

        txt_motacd.setColumns(20);
        txt_motacd.setRows(5);
        jScrollPane2.setViewportView(txt_motacd);

        lbl_chuyende.setText("Chuyên đề");

        cbx_chuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_chuyendeActionPerformed(evt);
            }
        });

        lbl_ngaykg.setText("Ngày khai giảng");

        lbl_hocphi.setText("Học phí");

        lbl_gio.setText("Thời lượng (giờ)");

        txt_hocphi.setEditable(false);
        txt_hocphi.setEnabled(false);

        txt_gio.setEditable(false);
        txt_gio.setEnabled(false);

        lbl_nguoitao.setText("Người tạo");

        lbl_ngaytao.setText("Ngày tạo");

        txt_nguoitao.setEditable(false);
        txt_nguoitao.setEnabled(false);

        txt_ngaytao.setEditable(false);
        txt_ngaytao.setEnabled(false);

        btn_hocvien.setText("Học Viên");
        btn_hocvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hocvienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_editLayout = new javax.swing.GroupLayout(pnl_edit);
        pnl_edit.setLayout(pnl_editLayout);
        pnl_editLayout.setHorizontalGroup(
            pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnl_editLayout.createSequentialGroup()
                        .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_nguoitao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_hocphi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_chuyende, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_chuyende, javax.swing.GroupLayout.Alignment.LEADING, 0, 297, Short.MAX_VALUE)
                                    .addComponent(lbl_hocphi, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(lbl_nguoitao)))
                        .addGap(25, 25, 25)
                        .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngaykg)
                            .addComponent(txt_gio)
                            .addGroup(pnl_editLayout.createSequentialGroup()
                                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_ngaytao)
                                    .addComponent(lbl_gio)
                                    .addComponent(lbl_ngaykg))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_ngaytao)))
                    .addGroup(pnl_editLayout.createSequentialGroup()
                        .addComponent(lbl_motacd)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnl_editLayout.createSequentialGroup()
                        .addComponent(btn_insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_editLayout.createSequentialGroup()
                                .addComponent(btn_hocvien)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnl_editLayout.createSequentialGroup()
                                .addComponent(btn_clear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_first)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_prev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_last)))))
                .addContainerGap())
        );
        pnl_editLayout.setVerticalGroup(
            pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_chuyende)
                    .addComponent(lbl_ngaykg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_chuyende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngaykg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_hocphi)
                    .addComponent(lbl_gio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hocphi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nguoitao)
                    .addComponent(lbl_ngaytao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_motacd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addGroup(pnl_editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete)
                    .addComponent(btn_clear)
                    .addComponent(btn_last)
                    .addComponent(btn_next)
                    .addComponent(btn_prev)
                    .addComponent(btn_first))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hocvien)
                .addGap(16, 16, 16))
        );

        tabs.addTab("CẬP NHẬT", pnl_edit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tabs))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_title)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_GridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GridViewMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbl_GridView.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_tbl_GridViewMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        if(!UHelper.checkNull_JTextField(txt_ngaykg, "Ngày KG ")){
            return;
        }
        if(!UHelper.checkNull_JTextArea(txt_motacd, "Ghi chú ")){
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

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        // TODO add your handling code here:
        this.index = tbl_GridView.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btn_lastActionPerformed

    private void cbx_chuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_chuyendeActionPerformed
        // TODO add your handling code here:
        this.selectComboBox();
    }//GEN-LAST:event_cbx_chuyendeActionPerformed

    private void btn_hocvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hocvienActionPerformed
        // TODO add your handling code here:
        this.openHocVien();
    }//GEN-LAST:event_btn_hocvienActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        this.fillComboBox();
        this.load();
        this.clear();
        this.setStatus(true);
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_hocvien;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox cbx_chuyende;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_chuyende;
    private javax.swing.JLabel lbl_gio;
    private javax.swing.JLabel lbl_hocphi;
    private javax.swing.JLabel lbl_motacd;
    private javax.swing.JLabel lbl_ngaykg;
    private javax.swing.JLabel lbl_ngaytao;
    private javax.swing.JLabel lbl_nguoitao;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JPanel pnl_edit;
    private javax.swing.JPanel pnl_list;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbl_GridView;
    private javax.swing.JTextField txt_gio;
    private javax.swing.JTextField txt_hocphi;
    private javax.swing.JTextArea txt_motacd;
    private javax.swing.JTextField txt_ngaykg;
    private javax.swing.JTextField txt_ngaytao;
    private javax.swing.JTextField txt_nguoitao;
    // End of variables declaration//GEN-END:variables
}
