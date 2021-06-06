import java.awt.Color;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ProcessingFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private String selectedFunction = null;
	/**
     * Creates new form ProcessingFrame
     */
	
    public ProcessingFrame() {
        initComponents();
        run();
    }

	/**
     * This method is called from within the constructor to initialize the form.
     */
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        label1 = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox();
        label2 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        checkbox1 = new java.awt.Checkbox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Systeme Simulator");
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true);
        jTextArea1.setBackground(Color.black);
        jTextArea1.setForeground(Color.white);
        jScrollPane1.setViewportView(jTextArea1);

        label1.setFont(new java.awt.Font("Delilah", 1, 14)); 
        label1.setText("Choose Fonction :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "adduser", "cat", "chmod", "chown", "cp", "dump", "find", "gpasswd","groupadd","lmod","ls","mkdir",/*"mkfs",*/"passwd",/*"tee",*/"umask","useradd" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        label2.setFont(new java.awt.Font("Delilah", 1, 14)); 
        label2.setText("Set Arguments :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        checkbox1.setFont(new java.awt.Font("Delilah", 1, 14)); // NOI18N
        checkbox1.setLabel("Disable arguments");

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jMenuBar1.add(jMenu1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem1);
        
        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        jMenu2.add(jMenuItem3);
        jMenu2.add(jMenuItem4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private String selectedString(ItemSelectable selectedItem) {
    	Object selected[] = selectedItem.getSelectedObjects();
        return ((selected.length == 0) ? "null" : (String)selected[0]);
	}
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

 
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
    	final Vector<String> args = new Vector<String>() ;
    	String[] argArray = jTextField1.getText().split(" ");
    	for(int i = 0;i < argArray.length;i++)
    		args.add(argArray[i]);
    	switch (selectedFunction){
    		case "ls" : ls.main(args);
    					
    					break;
    		case "cp" : cp.main(args);
						break;
    		case "adduser" : adduser.main(args);
						break;
    		case "cat" : cat.main(args);
						break;
    		case "chmod" : chmod.main(args);
						break;
    		case "chown" : chown.main(args);
						break;
    		case "find" : find.main(args);
						break;
    		case "gpasswd" : gpasswd.main(args);
						break;
    		case "groupadd" : groupadd.main(args);
						break;
    		case "lmod" : lmod.main(args);
						break;
    		case "mkdir" : mkdir.main(args);
						break;
    		/*case "mkfs" : mkfs.main(args);
						break;*/
    		case "passwd" : passwd.main(args);
						break;
    		/*case "tee" : tee.main(args);
						break;*/
    		case "umask" : umask.main(args);
						break;
    		case "useradd" : useradd.main(args);
						break;
    		case "dump" : dump.main(args);
    					break;
    	}
    	System.out.println("hamine@hamine:");
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	System.exit(0);
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    	ItemSelectable selectedItem = (ItemSelectable)evt.getSource();
    	selectedFunction = selectedString(selectedItem);
    }

    
    
    public void run() {
        redirectSystemStreams();
        System.out.println("hamine@hamine:");
    }
    
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            jTextArea1.append(text);
            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
          }
        });
      }

      private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
          @Override
          public void write(int b) throws IOException {
            updateTextArea(String.valueOf((char) b));
          }

          @Override
          public void write(byte[] b, int off, int len) throws IOException {
            updateTextArea(new String(b, off, len));
          }

          @Override
          public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
          }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
      }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcessingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProcessingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private String tempMSG;
    // End of variables declaration
}
