/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import com.polypro.helper.DialogHelper;
import com.polypro.helper.ShareHelper;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * TTee Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        init();
    }

    void init() {
        setSize(1000, 600);
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_dongho.setText(format.format(new Date()));
            }
        }).start();
        this.openWelcome();
        this.openLogin();
    }

    void openLogin() {
        new DangNhapJDialog(this, true).setVisible(true);
    }

    void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    void logoff() {
        ShareHelper.logoff();
        this.openLogin();
    }

    void exit() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn kết thúc?")) {
            System.exit(0);
        }
    }

    void openDoiMatKhau() {
        if (ShareHelper.authenticated()) {
            new DoiMatKhauJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    void openHocSinh() {
        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        HocSinhJInternalFrame cn = new HocSinhJInternalFrame();
        cn.setTitle("HocSinh");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
    }

    void openThongKe(int index) {

        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        ThongKeJInternalFrame cn = new ThongKeJInternalFrame();
        cn.setTitle("Thống Kê");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
    }

    void openNhanVien() {
//Code open này là bật JFrame thường chứu không phải là JInternalFrame 
     /*   if (ShareHelper.authenticated()) {
         new NhanVienJInternalFrame().setVisible(true);
         } else {
         DialogHelper.alert(this, "Vui lòng đăng nhập!");
         }*/
        //Sửa code các form open thành như này
        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        NhanVienJInternalFrame cn = new NhanVienJInternalFrame();
        cn.setTitle("Nhân viên");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
        //sửa form ThongKe như thế nhưng có thêm cái index thôi
    }

    void openKhoaHoc() {
        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        KhoaHocJInternalFrame cn = new KhoaHocJInternalFrame();
        cn.setTitle("Khóa Học");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
    }

    void openChuyenDe() {
        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        ChuyenDeJInternalFrame cn = new ChuyenDeJInternalFrame();
        cn.setTitle("Chuyên Đề");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
    }

    void openNguoiHoc() {
        for (JInternalFrame fr : jDesktopPane1.getAllFrames()) {
            fr.dispose();
        }
        NguoiHocJInternalFrame cn = new NguoiHocJInternalFrame();
        cn.setTitle("Người Học");
        if (cn == null || cn.isClosed()) {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        } else {
            cn.setLocation(this.getWidth() / 2 - cn.getWidth() / 2,
                    (this.getHeight() - 20) / 2 - cn.getHeight() / 2 - 20);
            jDesktopPane1.add(cn);
            cn.setVisible(true);
        }
    }

    void openAbout() {
        new GioiThieuJDialog(this, true).setVisible(true);
    }

    void openWebsite() {
        try {
            Desktop.getDesktop().browse(new File("help/index.html").toURI());
        } catch (IOException ex) {
            DialogHelper.alert(this, "Không tìm thấy file hướng dẫn!");
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

        jToolBar1 = new javax.swing.JToolBar();
        btn_dangxuat = new javax.swing.JButton();
        btn_ketthuc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_chuyende = new javax.swing.JButton();
        btn_nguoihoc = new javax.swing.JButton();
        btn_khoahoc = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_huongdan = new javax.swing.JButton();
        btn_HocSinh = new javax.swing.JButton();
        pnl_trangthai = new javax.swing.JPanel();
        lbl_trangthai = new javax.swing.JLabel();
        lbl_dongho = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            protected void  paintComponent (Graphics g){
                Image img = new ImageIcon(this.getClass().getResource("/icon/logo.png")).getImage();
                try {
                    Graphics g2d = (Graphics2D)g;
                    double x= img.getWidth(null);
                    double y= img.getHeight(null);
                    // g2d.scale(getWidth()/x,getHeight()/y);
                    // g2d.drawImage(img, 0, 0, this);
                    g2d.drawImage(img, this.getWidth()/4+100,this.getHeight()/4-100,this);
                } catch (Exception e) {
                    System.out.println("Gap loi");
                }
            }

        };
        mnub_main = new javax.swing.JMenuBar();
        mnu_hethong = new javax.swing.JMenu();
        mni_dangnhap = new javax.swing.JMenuItem();
        mni_dangxuat = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mni_doimatkhau = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mni_ketthuc = new javax.swing.JMenuItem();
        mnu_quanly = new javax.swing.JMenu();
        mni_QLnguoihoc = new javax.swing.JMenuItem();
        mni_QLchuyende = new javax.swing.JMenuItem();
        mni_QLkhoahoc = new javax.swing.JMenuItem();
        mni_QLnhanvien = new javax.swing.JMenuItem();
        mnu_thongke = new javax.swing.JMenu();
        mni_TKnguoihoc = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mni_TKbangdiem = new javax.swing.JMenuItem();
        mni_TKdiemtungkhoa = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mni_TKdoanhthu = new javax.swing.JMenuItem();
        mnu_trogiup = new javax.swing.JMenu();
        mni_TGhuongdan = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mni_TGgioithieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");

        jToolBar1.setRollover(true);

        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        btn_dangxuat.setText("Đăng xuất");
        btn_dangxuat.setFocusable(false);
        btn_dangxuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_dangxuat.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_dangxuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_dangxuat);

        btn_ketthuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Stop.png"))); // NOI18N
        btn_ketthuc.setText("Kết thúc");
        btn_ketthuc.setFocusable(false);
        btn_ketthuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ketthuc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_ketthuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ketthucActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_ketthuc);
        jToolBar1.add(jSeparator1);

        btn_chuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        btn_chuyende.setText("Chuyên đề");
        btn_chuyende.setFocusable(false);
        btn_chuyende.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_chuyende.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_chuyende.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_chuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chuyendeActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_chuyende);

        btn_nguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conference.png"))); // NOI18N
        btn_nguoihoc.setText("Người học");
        btn_nguoihoc.setFocusable(false);
        btn_nguoihoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nguoihoc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_nguoihoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nguoihocActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_nguoihoc);

        btn_khoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Certificate.png"))); // NOI18N
        btn_khoahoc.setText("Khóa học");
        btn_khoahoc.setFocusable(false);
        btn_khoahoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_khoahoc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_khoahoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_khoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoahocActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_khoahoc);
        jToolBar1.add(jSeparator2);

        btn_huongdan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Globe.png"))); // NOI18N
        btn_huongdan.setText("Hướng dẫn");
        btn_huongdan.setFocusable(false);
        btn_huongdan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_huongdan.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btn_huongdan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_huongdan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huongdanActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_huongdan);

        btn_HocSinh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\Dự Án Mẫu\\icon\\icon\\Clock.png")); // NOI18N
        btn_HocSinh.setText("Học Sinh");
        btn_HocSinh.setFocusable(false);
        btn_HocSinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_HocSinh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_HocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HocSinhActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_HocSinh);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        pnl_trangthai.setLayout(new java.awt.BorderLayout());

        lbl_trangthai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Info.png"))); // NOI18N
        lbl_trangthai.setText("Hệ quản lý đào tạo");
        pnl_trangthai.add(lbl_trangthai, java.awt.BorderLayout.CENTER);

        lbl_dongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Alarm.png"))); // NOI18N
        lbl_dongho.setText("10:55 PM");
        pnl_trangthai.add(lbl_dongho, java.awt.BorderLayout.LINE_END);

        getContentPane().add(pnl_trangthai, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        mnu_hethong.setText("Hệ Thống");

        mni_dangnhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mni_dangnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Key.png"))); // NOI18N
        mni_dangnhap.setText("Đăng nhập");
        mni_dangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_dangnhapActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_dangnhap);

        mni_dangxuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mni_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Log out.png"))); // NOI18N
        mni_dangxuat.setText("Đăng xuất");
        mni_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_dangxuatActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_dangxuat);
        mnu_hethong.add(jSeparator3);

        mni_doimatkhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        mni_doimatkhau.setText("Đổi mật khẩu");
        mni_doimatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_doimatkhauActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_doimatkhau);
        mnu_hethong.add(jSeparator4);

        mni_ketthuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mni_ketthuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Stop.png"))); // NOI18N
        mni_ketthuc.setText("Kết thúc");
        mni_ketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ketthucActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_ketthuc);

        mnub_main.add(mnu_hethong);

        mnu_quanly.setText("Quản Lý");

        mni_QLnguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conference.png"))); // NOI18N
        mni_QLnguoihoc.setText("Người học");
        mni_QLnguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLnguoihocActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_QLnguoihoc);

        mni_QLchuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        mni_QLchuyende.setText("Chuyên đề");
        mni_QLchuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLchuyendeActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_QLchuyende);

        mni_QLkhoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Certificate.png"))); // NOI18N
        mni_QLkhoahoc.setText("Khóa học");
        mni_QLkhoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLkhoahocActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_QLkhoahoc);

        mni_QLnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N
        mni_QLnhanvien.setText("Quản lý nhân viên");
        mni_QLnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_QLnhanvienActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_QLnhanvien);

        mnub_main.add(mnu_quanly);

        mnu_thongke.setText("Thống Kê");

        mni_TKnguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Clien list.png"))); // NOI18N
        mni_TKnguoihoc.setText("Người học từng năm");
        mni_TKnguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TKnguoihocActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_TKnguoihoc);
        mnu_thongke.add(jSeparator5);

        mni_TKbangdiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Card file.png"))); // NOI18N
        mni_TKbangdiem.setText("Bảng điểm khóa...");
        mni_TKbangdiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TKbangdiemActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_TKbangdiem);

        mni_TKdiemtungkhoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bar chart.png"))); // NOI18N
        mni_TKdiemtungkhoa.setText("Điểm từng khóa");
        mni_TKdiemtungkhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TKdiemtungkhoaActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_TKdiemtungkhoa);
        mnu_thongke.add(jSeparator6);

        mni_TKdoanhthu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dollar.png"))); // NOI18N
        mni_TKdoanhthu.setText("Doanh thu từng chuyên đề...");
        mni_TKdoanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TKdoanhthuActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_TKdoanhthu);

        mnub_main.add(mnu_thongke);

        mnu_trogiup.setText("Trợ Giúp");

        mni_TGhuongdan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mni_TGhuongdan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Globe.png"))); // NOI18N
        mni_TGhuongdan.setText("Hướng dẫn sử dụng");
        mni_TGhuongdan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TGhuongdanActionPerformed(evt);
            }
        });
        mnu_trogiup.add(mni_TGhuongdan);
        mnu_trogiup.add(jSeparator7);

        mni_TGgioithieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Brick house.png"))); // NOI18N
        mni_TGgioithieu.setText("Giới thiệu sản phẩm");
        mni_TGgioithieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_TGgioithieuActionPerformed(evt);
            }
        });
        mnu_trogiup.add(mni_TGgioithieu);

        mnub_main.add(mnu_trogiup);

        setJMenuBar(mnub_main);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mni_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_dangxuatActionPerformed
        // TODO add your handling code here:
        this.logoff();
    }//GEN-LAST:event_mni_dangxuatActionPerformed

    private void mni_ketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ketthucActionPerformed
        // TODO add your handling code here:
        this.exit();
    }//GEN-LAST:event_mni_ketthucActionPerformed

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        // TODO add your handling code here:
        this.logoff();
    }//GEN-LAST:event_btn_dangxuatActionPerformed

    private void mni_dangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_dangnhapActionPerformed
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_mni_dangnhapActionPerformed

    private void btn_ketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ketthucActionPerformed
        // TODO add your handling code here:
        this.exit();
    }//GEN-LAST:event_btn_ketthucActionPerformed

    private void btn_chuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chuyendeActionPerformed
        // TODO add your handling code here:
        this.openChuyenDe();
    }//GEN-LAST:event_btn_chuyendeActionPerformed

    private void btn_nguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nguoihocActionPerformed
        // TODO add your handling code here:
        this.openNguoiHoc();
    }//GEN-LAST:event_btn_nguoihocActionPerformed

    private void btn_khoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoahocActionPerformed
        // TODO add your handling code here:
        this.openKhoaHoc();
    }//GEN-LAST:event_btn_khoahocActionPerformed

    private void btn_huongdanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huongdanActionPerformed
        // TODO add your handling code here:
        this.openAbout();
    }//GEN-LAST:event_btn_huongdanActionPerformed

    private void mni_QLnguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLnguoihocActionPerformed
        // TODO add your handling code here:
        this.openNguoiHoc();
    }//GEN-LAST:event_mni_QLnguoihocActionPerformed

    private void mni_QLchuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLchuyendeActionPerformed
        // TODO add your handling code here:
        this.openChuyenDe();
    }//GEN-LAST:event_mni_QLchuyendeActionPerformed

    private void mni_QLkhoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLkhoahocActionPerformed
        // TODO add your handling code here:
        this.openKhoaHoc();
    }//GEN-LAST:event_mni_QLkhoahocActionPerformed

    private void mni_QLnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_QLnhanvienActionPerformed
        // TODO add your handling code here:
        this.openNhanVien();
    }//GEN-LAST:event_mni_QLnhanvienActionPerformed

    private void mni_TKnguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TKnguoihocActionPerformed
        // TODO add your handling code here:
        this.openThongKe(0);

    }//GEN-LAST:event_mni_TKnguoihocActionPerformed

    private void mni_TGhuongdanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TGhuongdanActionPerformed
        // TODO add your handling code here:
        this.openAbout();
    }//GEN-LAST:event_mni_TGhuongdanActionPerformed

    private void mni_TGgioithieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TGgioithieuActionPerformed
        // TODO add your handling code here:
        this.openWebsite();
    }//GEN-LAST:event_mni_TGgioithieuActionPerformed

    private void mni_TKbangdiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TKbangdiemActionPerformed
        // TODO add your handling code here:
        this.openThongKe(1);
    }//GEN-LAST:event_mni_TKbangdiemActionPerformed

    private void mni_TKdiemtungkhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TKdiemtungkhoaActionPerformed
        // TODO add your handling code here:
        this.openThongKe(2);
    }//GEN-LAST:event_mni_TKdiemtungkhoaActionPerformed

    private void mni_TKdoanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_TKdoanhthuActionPerformed
        // TODO add your handling code here:
        this.openThongKe(3);
    }//GEN-LAST:event_mni_TKdoanhthuActionPerformed

    private void mni_doimatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_doimatkhauActionPerformed
        // TODO add your handling code here:
        this.openDoiMatKhau();
    }//GEN-LAST:event_mni_doimatkhauActionPerformed

    private void btn_HocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HocSinhActionPerformed
        // TODO add your handling code here:
        this.openHocSinh();
    }//GEN-LAST:event_btn_HocSinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_HocSinh;
    private javax.swing.JButton btn_chuyende;
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JButton btn_huongdan;
    private javax.swing.JButton btn_ketthuc;
    private javax.swing.JButton btn_khoahoc;
    private javax.swing.JButton btn_nguoihoc;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_dongho;
    private javax.swing.JLabel lbl_trangthai;
    private javax.swing.JMenuItem mni_QLchuyende;
    private javax.swing.JMenuItem mni_QLkhoahoc;
    private javax.swing.JMenuItem mni_QLnguoihoc;
    private javax.swing.JMenuItem mni_QLnhanvien;
    private javax.swing.JMenuItem mni_TGgioithieu;
    private javax.swing.JMenuItem mni_TGhuongdan;
    private javax.swing.JMenuItem mni_TKbangdiem;
    private javax.swing.JMenuItem mni_TKdiemtungkhoa;
    private javax.swing.JMenuItem mni_TKdoanhthu;
    private javax.swing.JMenuItem mni_TKnguoihoc;
    private javax.swing.JMenuItem mni_dangnhap;
    private javax.swing.JMenuItem mni_dangxuat;
    private javax.swing.JMenuItem mni_doimatkhau;
    private javax.swing.JMenuItem mni_ketthuc;
    private javax.swing.JMenu mnu_hethong;
    private javax.swing.JMenu mnu_quanly;
    private javax.swing.JMenu mnu_thongke;
    private javax.swing.JMenu mnu_trogiup;
    private javax.swing.JMenuBar mnub_main;
    private javax.swing.JPanel pnl_trangthai;
    // End of variables declaration//GEN-END:variables
}
