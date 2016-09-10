package com.parking.telas;

import com.parking.entity.Veiculo;
import com.parking.files.PrintLogger;
import com.parking.frame.TelaPrincipal;
import com.parking.regrasnegocios.Persistencia;
import com.parking.telasConsulta.TelaConsultaVeiculo;
import javax.swing.JOptionPane;

public class TelaVeiculo extends javax.swing.JDialog {

    //Objeto responsavel por imprimir em arquivo as eventuais excessões geradas pela aplicação
    private PrintLogger logger = new PrintLogger();
    //Objeto responsavel por realizar a iteracao com a camada de persistencia
    private Persistencia persistencia;
    //Objeto responsavel por armazenar as caracteristicas de um veiculo
    private Veiculo veiculo = new Veiculo();
    //Objeto responsavel por instanciar a tela de que exibira o resultado da consulta feita no banco de dadps
    private TelaConsultaVeiculo telaConsultaVeiculo;
    //Objeto responsavel por armazenar uma instancia da tela principal
    private TelaPrincipal telaPrincipal;
    //Objeto responsavel por armazenar uma instancia da tela de cadastro de reserva
    private TelaReserva telaReserva;

    /**
     *
     * @param parent
     * @param modal
     * @param persistencia
     * @param principal
     */
    public TelaVeiculo(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaPrincipal principal) {
        super(parent, modal);
        try {
            initComponents();
            //inicializo o objeto responsavel por iteragir com o banco de dados
            this.persistencia = persistencia;
            this.telaPrincipal = principal;
            //desabilito os botoes da paleta de cadastro
            this.botoesEditaveis(false);
            // desabilito a edicao do formulario de cadastro
            this.habilitarCampos(false);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "public TelaVeiculo()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     *
     * @param parent
     * @param modal
     * @param persistencia
     * @param telaReserva
     */
    public TelaVeiculo(java.awt.Frame parent, boolean modal, Persistencia persistencia, TelaReserva telaReserva) {
        super(parent, modal);
        try {
            initComponents();
            //inicializo o objeto responsavel por iteragir com o banco de dados
            this.persistencia = persistencia;
            this.telaReserva = telaReserva;
            //desabilito os botoes da paleta de cadastro
            this.botoesEditaveis(false);
            // desabilito a edicao do formulario de cadastro
            this.habilitarCampos(false);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "public TelaVeiculo()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * Metodo responsavel por tornar editavel os botoes da paleta de cadastro
     *
     * @param visivel recebe um dado booleano, sendo verdadeiro se for editavel
     * e falso se nao for
     */
    private void botoesEditaveis(boolean visivel) {
        try {
            this.btnSalvar.setEnabled(visivel);
            this.btnAltera.setEnabled(visivel);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void botoesEditaveis()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * Metodo responsavel por tornar editavel o formulario de cadastro
     *
     * @param habilitar recebe um dado booleano, sendo verdadeiro se for
     * editavel e falso se nao for
     */
    private void habilitarCampos(boolean habilitar) {
        try {
            if (habilitar) {
                this.txtDescricao.setEnabled(habilitar);
                this.txtMarca.setEnabled(habilitar);
                this.txtModelo.setEnabled(habilitar);
                this.txtDescricao.requestFocus();
            } else {
                this.txtDescricao.setEnabled(habilitar);
                this.txtMarca.setEnabled(habilitar);
                this.txtModelo.setEnabled(habilitar);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void habilitarCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * Método responsavel por validar se todos os campos do formulario foram
     * preenchidos
     *
     * @return um dado booleano sendo verdadeiro se todos os dados estao
     * preenchidos e false se o mesmo nao estiver
     */
    private boolean validarInformacao() {
        try {
            // se o campo codigo  for vazio
            if (txtDescricao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "INFORME A DESCRIÇÃO DO VEÍCULO.");
                this.txtDescricao.requestFocus();
                return false;
                // se o campo marca  for vazio    
            } else if (txtMarca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "INFORME A MARCA DO VEÍCULO.");
                this.txtMarca.requestFocus();
                return false;
                // se o campo modelo for vazio   
            } else if (txtModelo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "INFORME O MODELO DO VEÍCULO.");
                this.txtModelo.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private boolean validarInformacao()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
        return true;

    }

    /**
     * Metodo responsavel por receber um objeto veiculo selecionado pelo usuario
     * na tela de consulta
     *
     * @param veiculo recebe como parametro um objeto do tipo veiculo
     */
    public void receberVeiculo(Veiculo veiculo) {
        try {
            this.veiculo = veiculo;
            this.txtCodigo.setText(String.valueOf(this.veiculo.getCodigo()));
            this.txtDescricao.setText(this.veiculo.getDescricao());
            this.txtMarca.setText(this.veiculo.getMarcar());
            this.txtModelo.setText(this.veiculo.getModelo());
            this.btnAltera.setEnabled(true);
            this.btnSalvar.setEnabled(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "public void receberVeiculo()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por carregar os dados contidos no formulario para um
     * objeto do tipo veiculo
     */
    private void configurarVeiculo() {
        try {
            // se o campo codigo nao for vazio
            if (!txtCodigo.getText().isEmpty()) {
                this.veiculo.setCodigo(Integer.parseInt(txtCodigo.getText()));
            }
            this.veiculo.setDescricao(txtDescricao.getText());
            this.veiculo.setMarcar(txtMarca.getText());
            this.veiculo.setModelo(txtModelo.getText());
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void configurarVeiculo()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por limpar os campos do formulario de cadastro
     */
    private void limparCampos() {
        try {
            this.txtCodigo.setText("");
            this.txtDescricao.setText("");
            this.txtMarca.setText("");
            this.txtModelo.setText("");
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void limparCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundoVeiculo = new javax.swing.JPanel();
        ToolBarSuperior = new javax.swing.JToolBar();
        btnAltera = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtCodigo = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JFormattedTextField();
        txtModelo = new javax.swing.JFormattedTextField();
        txtMarca = new javax.swing.JFormattedTextField();
        lblCodigo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PARKING MANAGEMENT SYSTEM - Cadastro de Veículos");
        setResizable(false);

        painelFundoVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        painelFundoVeiculo.setPreferredSize(new java.awt.Dimension(550, 300));

        ToolBarSuperior.setBackground(new java.awt.Color(255, 255, 0));
        ToolBarSuperior.setRollover(true);

        btnAltera.setBackground(new java.awt.Color(255, 255, 255));
        btnAltera.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAltera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/ALTERAR_INTERNALFRAME.png"))); // NOI18N
        btnAltera.setText("ALTERAR");
        btnAltera.setFocusable(false);
        btnAltera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAltera.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlteraActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnAltera);

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/NOVO_INTERNALFRAME.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnNovo);

        btnPesquisar.setBackground(new java.awt.Color(255, 255, 255));
        btnPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/PESQUISAR_INTERNALFRAME.png"))); // NOI18N
        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.setFocusable(false);
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnPesquisar);

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/SALVAR_INTERNALFRAME.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnSalvar);

        btnSair.setBackground(new java.awt.Color(255, 255, 255));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/SAIR_INTERNAL FRAME.png"))); // NOI18N
        btnSair.setText("FECHAR");
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        ToolBarSuperior.add(btnSair);

        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setEnabled(false);

        lblCodigo.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigo.setText("Código");

        lblDescricao.setBackground(new java.awt.Color(255, 255, 255));
        lblDescricao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDescricao.setText("Descrição Veículo");

        lblMarca.setBackground(new java.awt.Color(255, 255, 255));
        lblMarca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMarca.setText("Marca do Veículo");

        lblModelo.setBackground(new java.awt.Color(255, 255, 255));
        lblModelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblModelo.setText("Modelo do Veiculo");

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/veiculo.png"))); // NOI18N

        javax.swing.GroupLayout painelFundoVeiculoLayout = new javax.swing.GroupLayout(painelFundoVeiculo);
        painelFundoVeiculo.setLayout(painelFundoVeiculoLayout);
        painelFundoVeiculoLayout.setHorizontalGroup(
            painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBarSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                        .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMarca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoVeiculoLayout.createSequentialGroup()
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)))
                                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescricao)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblImagem))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                        .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblModelo))
                            .addComponent(txtModelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(158, 158, 158))))
        );
        painelFundoVeiculoLayout.setVerticalGroup(
            painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                .addComponent(ToolBarSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(lblDescricao))
                .addGap(4, 4, 4)
                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelFundoVeiculoLayout.createSequentialGroup()
                        .addComponent(lblMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblImagem))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteraActionPerformed
        try {
            // se o campo de texto for vazio
            if (this.txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não é permitido alterar um registro não salvo!");
                this.habilitarCampos(false);
                this.botoesEditaveis(false);
            } else {
                this.habilitarCampos(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void btnAlteraActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnAlteraActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            if (!txtCodigo.getText().isEmpty()) {
                int resposta = JOptionPane.showConfirmDialog(this, "Deseja realemente iniciar um novo cadastro ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    //habilito os campos do formulario
                    this.habilitarCampos(true);
                    //habilito os botoes da paleta de cadastro
                    this.botoesEditaveis(true);
                    //limpo os campso
                    this.limparCampos();
                }
            } else {
                //habilito os campos do formulario
                this.habilitarCampos(true);
                //habilito os botoes da paleta de cadastro
                this.botoesEditaveis(true);
                //limpo os campso
                this.limparCampos();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void btnNovoActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaConsultaVeiculo.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaConsultaVeiculo.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, " private void btnPesquisarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (validarInformacao()) {
                this.configurarVeiculo();
                if (txtCodigo.getText().isEmpty()) {
                    persistencia.inserirObjeto(veiculo);
                    JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
                    this.botoesEditaveis(false);
                    this.limparCampos();
                    this.habilitarCampos(false);
                } else {
                    persistencia.atualizarObjeto(veiculo);
                    JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
                    this.botoesEditaveis(false);
                    this.limparCampos();
                    this.habilitarCampos(false);
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void btnSalvarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        try {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair ?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaVeiculo.class, "private void btnSairActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBarSuperior;
    private javax.swing.JButton btnAltera;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JPanel painelFundoVeiculo;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JFormattedTextField txtDescricao;
    private javax.swing.JFormattedTextField txtMarca;
    private javax.swing.JFormattedTextField txtModelo;
    // End of variables declaration//GEN-END:variables
}
