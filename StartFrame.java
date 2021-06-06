import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class StartFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame startFrame;
	/**
     * Creates new form StartFrame
     */
    public StartFrame() {
        initComponents();
    }

    /*
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 =new javax.swing.JMenuItem();
        jMenuItem2 =new javax.swing.JMenuItem();
        jMenuItem3 =new javax.swing.JMenuItem();
        jMenuItem4 =new javax.swing.JMenuItem();
        
        jProgressBar1.setForeground(Color.blue);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Systeme Simulator");
        
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Terminal-icon.png")));
        jButton1.setText("     Start");
        jButton1.setName("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moss.png")));
        
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.gc();
				System.exit(EXIT_ON_CLOSE);
			}
		});
        jMenuItem2.setText("Restart");
        jMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				new StartFrame();
			}
		});
        jMenuItem3.setText("Help");
        jMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This software is designed to simulate the file system simulator.\n"
						+ "The program have a list of functions availble to use\n"
						+ "1- useradd : add user to the file system.\n"
						+ "2- passwd : create user password.\n"
						+ "3- adduser : add user to group.\n"
						+ "4- groupadd : add a group.\n"
						+ "5- gpasswd : create group password.\n"
						+ "6- chmod : change the right access of file.\n"
						+ "7- lmod : show the right access of file.\n"
						+ "8- umask : define the defult right access of file\n"
						+ "9- chown : change the owner of file.\n"
						+ "You have to enter an additional argument for each function.", 
		    			"Help", 
		  			  JOptionPane.INFORMATION_MESSAGE);
			}
		});
        jMenuItem4.setText("About");
        jMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "MOSS simulateur updated and designed by :\n"
						+ " - Med Hassine Ben Taieb\n"
						+ " - Amine Haddad\n"
						+ "Student of Science University of Tunis,Tunisia", 
		    			"About", 
		  			  JOptionPane.INFORMATION_MESSAGE);
			}
		});
        jMenu1.setText("File");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem1);
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.add(jMenuItem3);
        jMenu2.add(jMenuItem4);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	jButton1.setEnabled(false);
    	new Thread(new thread1()).start();//Start the thread 
    }

    public static class thread1 implements Runnable{
    	public void run(){
    		for (int i=0; i<=100; i++){ //Progressively increment variable i
    			jProgressBar1.setValue(i); //Set value
    	        jProgressBar1.repaint(); //Refresh graphics
    	        try{Thread.sleep(15);} //Sleep 15 milliseconds
    	        catch (InterruptedException err){}	
    	    }
    		closeFrame(startFrame);
    		new ProcessingFrame().setVisible(true);
    	}
    }

    private static void closeFrame(JFrame frame){
    	frame.setVisible(false);
    	frame.dispose();
    }
    
    
    public static void main(String args[]) {
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                startFrame = new StartFrame();
                startFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    static javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration
}
