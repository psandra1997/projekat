/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma.otpremnica;

import domen.Otpremnica;
import domen.Prodavac;
import domen.StavkaOtpremnice;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.ControllerKlijent;

/**
 *
 * @author Sandra
 */
public class DijalogStampanje extends javax.swing.JDialog {
Otpremnica o;
    /**
     * Creates new form DijalogStampanje
     */
    public DijalogStampanje(java.awt.Frame parent, boolean modal,Otpremnica o) throws Exception {
        super(parent, modal);
        initComponents();
        this.o=o;
        setLocationRelativeTo(null);
        srediPolje();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtStampanje = new javax.swing.JTextArea();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stampanje");
        setResizable(false);

        txtStampanje.setColumns(20);
        txtStampanje.setRows(5);
        jScrollPane1.setViewportView(txtStampanje);

        btnPrint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPrint.setText("Stampanje");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint)
                .addGap(338, 338, 338))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            txtStampanje.print();
        } catch (PrinterException ex) {
            Logger.getLogger(DijalogStampanje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtStampanje;
    // End of variables declaration//GEN-END:variables

    private void srediPolje() throws Exception {
        ArrayList<StavkaOtpremnice> lista = ControllerKlijent.getInstanca().vratiStavkeZaOtpremnicu(o);
        Prodavac prodavac=ControllerKlijent.getInstanca().getProdavac();
        StringBuilder sb = new StringBuilder();
        for (StavkaOtpremnice s : lista) {
            sb.append(s.getRB());
            sb.append("\b\b");
            sb.append(s.getMobilniTelefon());
            sb.append("\b\t");
            sb.append(s.getJedinicaMere());
            sb.append("\b\b\b\b");
            sb.append(s.getKolicina());
            sb.append("\b\b\t");
            sb.append(s.getCena());
            sb.append("\b\b");
            sb.append(s.getUkupanIznos());
            sb.append("\b\b");
            sb.append("\n\n");
        }
        String stringStavki = sb.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        txtStampanje.append("\t\t OTPREMNICA \n\n"
                + "Broj otpremnice: \t" + o.getBrojOtpremnice() + " \n"
                + "Iz magacina: \t\t" + o.getMagacin() + "\n"
                + "Poslato u: \t\t" + o.getProdavnica() + "\n"
                + "Poslao radnik: \t" + o.getRadnik() + "\n\n"
                + "Datum: \t\t" + sdf.format(System.currentTimeMillis())
                + "\n\nRB \b\b   ARTIKAL\t    JM\b    KOL\b    CENA\b   UKUPNO \n\n" + stringStavki
                + "\n\n UKUPNO \t\t" + o.getUkupanIznosStavki() + " dinara \n"
                + " PDV \t\t" + o.getPDV() + " % \n"
                + " VREDNOST \t\t" + o.getUkupnaVrednost() + " dinara \n\n"
                +"Prodavac: "+prodavac);
    }
}