package com.parking.telas;

import com.parking.entity.Cliente;
import com.parking.entity.Reserva;
import com.parking.entity.Vaga;
import com.parking.entity.Veiculo;
import com.parking.files.FileRead;
import com.parking.files.PrintLogger;
import com.parking.frame.TelaPrincipal;
import com.parking.regrasnegocios.GeradorRelatorio;
import com.parking.regrasnegocios.Persistencia;
import com.parking.telasConsulta.TelaConsultaCliente;
import com.parking.telasConsulta.TelaConsultaReserva;
import com.parking.telasConsulta.TelaConsultaVaga;
import com.parking.telasConsulta.TelaConsultaVeiculo;
import com.parking.uteis.ConversorData;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Diovani Bernardi da Motta
 */
public class TelaReserva extends javax.swing.JDialog {

    //Objeto responsavel por realizar a impressao de eventuais erros gerados pela aplicação no arquivo de log
    private PrintLogger logger = new PrintLogger();
    //Objeto responsavel por armazenar uma lista de Objetos do tipo Vaga
    private ArrayList<Vaga> lstVagas = new ArrayList<>();
    //Objeto responsavel por armazenar uma lista de Objetos do tipo Reserva
    private ArrayList<Reserva> lstReservas = new ArrayList<>();
    //Objeto responsavel por armazenar uma instancia da tela do objeto responsavel por realizar a iteracao com a persistencia
    private Persistencia persistencia = new Persistencia();
    //Objeto responsavel por armazenar uma instancia da tela da tela principal do programa
    private TelaPrincipal telaPrincipal;
    //Objeto responsavel por instanciar a tela de consulta de vagas
    private TelaConsultaVaga telaConsultaVaga;
    //Objeto responsavel por instanciar a tela de consulta de veiculo
    private TelaConsultaVeiculo telaConsultaVeiculo;
    //Objeto responsavel por instanciar a tela de consulta de clientes
    private TelaConsultaCliente telaConsultaCliente;
    // Objeto responsavel por armazenar as caracteristicas de uma vaga
    private Vaga vaga = new Vaga();
    //Objeto responsavel por armazenar as caracteristicas de um objeto veiculo
    private Veiculo veiculo = new Veiculo();
    //Objeto responsavel por armazenar as caracteristicas de um objeto do tipo cliente
    private Cliente cliente = new Cliente();
    //Objeto responsavel por armazenar as caracteristicas de um objeto do tipo reserva
    private Reserva reserva = new Reserva();
    // Objeto responsavel por instanciar a tela de cadastro de cliente
    private TelaCliente telaCliente;
    //Objeto responsavel por instanciar a tela de cadastro de veiculo
    private TelaVeiculo telaVeiculo;
    //Objeto responsavel por instanciar a tela de cadastro de 
    private TelaVaga telaVaga;
    //Objeto que tem a responsabilidade de converter  as datas
    private ConversorData conversorData = new ConversorData();
    //Objeto responsavel por instanciar a tela de consulta que exibirá os registros retornados do banco de dados
    private TelaConsultaReserva telaConsultaReserva;
    //Objeto responsavel por gerar a visualização do comprovante de Locação
    private GeradorRelatorio geradorRelatorio;
    //Objeto responsavel por intanciar uma Janela de Dialago que exibira o relatorio
    private JDialog visualizacaoRelatorio = new JDialog(this, "PARKING MANAGEMENT SYSTEM - Comprovante de Locação", true);
    //Objeto responsavel por retornar a configuracao existente no arquivo config.ini
    private FileRead fileRead = new FileRead();

    /**
     *
     * @param parent
     * @param modal
     * @param telaPrincipal
     * @param persistencia
     */
    public TelaReserva(java.awt.Frame parent, boolean modal, TelaPrincipal telaPrincipal, Persistencia persistencia) {
        super(parent, modal);
        try {
            initComponents();
            //Inicializacao dos Objetos recebidos por parametro
            this.persistencia = persistencia;
            this.telaPrincipal = telaPrincipal;
            //chamada ao metodo responsavel por configurar as vagas cadastradas
            this.carregarQuantidadeVagasCadastradas();
            //desabilito os formulario com os dados da reserva
            this.habilitarFormulario(false);
            //desabilito os botoes da paleta de cadastro
            this.botoesEditaveis(false);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtCodigoCliente.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtCodigoVeiculo.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
            //metodo utilizado para tratar os eventos de tab no campo de texto
            this.txtPlaca.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public TelaReserva()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que irá retornar da persistencia o numero de vagas cadastradas
     * para o estacionamento
     */
    public void carregarQuantidadeVagasCadastradas() {
        try {
            // variaveis responsaveis por armazenar o totais de vagas;
            int totalVagas;
            int totalOcupadas = 0;
            //retorno do banco de dados todas as vagas cadastradas
            this.lstVagas = this.persistencia.getLstVagas();
            //retorno do banco de dados todos as reservas cadastradas
            this.lstReservas = this.persistencia.getLstReservas();
            //capturo o total de vagas cadastradas 
            totalVagas = this.lstVagas.size();
            //itero todos os objetos da lista
            for (Vaga vaga : lstVagas) {
                //declaro um objeto do tipo button
                JButton btnVagas = new JButton();
                //seto o texto do mesmo para como o numero da vaga
                btnVagas.setText(String.valueOf(vaga.getNumeroVaga()));
                //seto as bordas para nulo
                btnVagas.setBorder(null);
                // carrego a imagem para o botao
                btnVagas.setIcon(new ImageIcon("image/GARAGEM_SMALL.png"));
                //adiciono o tratamento de eventos de click nos botoes
                btnVagas.addActionListener(new ClickBotao());
                //se a vaga estiver livre
                if (vaga.isStatus()) {
                    //pinto o botao com a cor verde
                    btnVagas.setBackground(Color.GREEN);
                } else {
                    //pinto o botao com a cor vermelha
                    btnVagas.setBackground(Color.RED);
                    //inclemento a variavel que controla o total de vagas ocupadas
                    totalOcupadas++;
                }
                //adiciono ao painel o botao recem criado
                this.painelvagas.add(btnVagas);
                //repinto a tela atual
                this.painelvagas.repaint();
                //revalido a estrutura do painel de vagas
                this.painelvagas.revalidate();
            }
            // seto os dados para os rotulos
            this.lblVagasOcupadas.setText("VAGAS OCUPADAS : " + totalOcupadas);
            this.lblVagasOcupadas.setFont(new Font("tahoma", Font.BOLD, 12));
            this.lblVagasOcupadas.setForeground(Color.red);
            // seto os dados para os rotulos
            this.lblVagasTotais.setText(" TOTAL VAGAS : " + totalVagas);
            this.lblVagasTotais.setFont(new Font("tahoma", Font.BOLD, 12));
            this.lblVagasTotais.setForeground(Color.GREEN);

            //repinto a tela atual
            this.repaint();
            //revalido a strutura da tela
            this.revalidate();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void carregarQuantidadeVagasCadastradas()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de tornar ou nao visivel o formulario
     * com os dados cadastrais
     *
     * @param visivel recebe um dado booleano, sendo true quando o mesmo for
     * visivel e falso quando nao for
     */
    public void habilitarFormulario(boolean visivel) {
        try {
            if (visivel) {
                this.painelDadosReserva.setVisible(visivel);
                this.painelFundoVagas.setEnabled(visivel);
                this.painelvagas.setEnabled(visivel);
                this.txtAreaObservacao.setVisible(visivel);
                this.scroll.setVisible(visivel);
                this.txtAreaObservacao.setEnabled(visivel);
                //requisito o foco para o campo codigo cliente
                this.txtCodigoCliente.requestFocus();
            } else {
                this.painelDadosReserva.setVisible(visivel);
                this.painelFundoVagas.setEnabled(visivel);
                this.painelvagas.setEnabled(visivel);
                this.txtAreaObservacao.setVisible(visivel);
                this.scroll.setVisible(visivel);
                this.txtAreaObservacao.setEnabled(visivel);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void habilitarFormulario()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * esse metodo tem a responsabilidade de selecionar a vaga apontada pelo
     * usuario e setar um objeto do tipo vaga
     *
     * @param numeroVaga recebe como parametro o numero da vaga
     */
    private void selecionarVagaCadastrada(int numeroVaga) {
        try {
            //itero todos os objeto do tipo lista
            for (Vaga v : lstVagas) {
                // se o objeto iterado possuir o mesmo numero de vaga selecionado pelo usuario
                if (v.getNumeroVaga() == numeroVaga) {
                    //adiciono o objeto iterado para 
                    this.vaga = v;
                    // se a vaga nao estiver locada
                    if (this.vaga.isStatus()) {
                        //seto os dados para o formulario
                        this.txtCodigoVaga.setText(String.valueOf(this.vaga.getCodigo()));
                        this.txtReferenciaVaga.setText(this.vaga.getReferencia());
                        this.txtNumeroVaga.setText(String.valueOf(this.vaga.getNumeroVaga()));
                    } else {
                        //seto os dados para o formulario
                        this.txtCodigoVaga.setText(String.valueOf(this.vaga.getCodigo()));
                        this.txtReferenciaVaga.setText(this.vaga.getReferencia());
                        this.txtNumeroVaga.setText(String.valueOf(this.vaga.getNumeroVaga()));
                    }
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void selecionarVagaCadastrada()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de carregar as datas para o formulario
     * de cadastro
     */
    private void carregarDataEntrada() {
        try {
            //seto a data de entrada para o formulario
            this.txtDataEntrada.setText(conversorData.converteDataString(new Date()));
            // seto a hora de entrada para o formulario
            this.txtHoraEntrada.setText(conversorData.converterHoraString(new Date()));
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void carregarDataEntrada()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de carregar as datas para o formulario
     * de cadastro
     */
    private void carregarDataSaida() {
        try {
            //seto a data de entrada para o formulario
            this.txtDataSaida.setText(conversorData.converteDataString(new Date()));
            // seto a hora de entrada para o formulario
            this.txtHoraSaida.setText(conversorData.converterHoraString(new Date()));
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void carregarDataSaida()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por remover todas as vagas mostradas no painel
     */
    public void removerVagas() {
        try {
            this.painelvagas.removeAll();
            //repinto a tela atual
            this.repaint();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void removerVagas()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por receber um cliente selecionado na tela de consulta
     * do sistema
     *
     * @param cliente recebe como parametro um objeto do tipo cliente,
     * selecionado pelo usuario na tela de consulta e seto para o formulario
     */
    public void indicarCliente(Cliente cliente) {
        try {
            //inicializo o objeto do tipo cliente
            this.cliente = cliente;
            //seto os dados para o formulario
            this.txtCodigoCliente.setText(String.valueOf(this.cliente.getCodigo()));
            this.txtNomeCliente.setText(this.cliente.getNome());
            this.txtCPFCNPJ.setText(this.cliente.getCpf());
            this.txtCelular.setText(this.cliente.getCelular());
            this.txtTelefone.setText(this.cliente.getTelefone());
            //requisito o foco para o campo codigo veiculo
            this.txtCodigoVeiculo.requestFocus();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void indicarCliente()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por receber uma veiculo selecionad na tela de consulta
     * do sistema
     *
     * @param vec recebe como parametro um objeto do tipo veiculo selecionado
     * pelo usuario na tela de consulta e seta para o formulario
     */
    public void indicarVeiculo(Veiculo vec) {
        try {
            //inicializo o objeto do tipo veiculo
            this.veiculo = vec;
            //seto os dados para o formulario
            this.txtCodigoVeiculo.setText(String.valueOf(this.veiculo.getCodigo()));
            this.txtDescricaoVeiculo.setText(this.veiculo.getDescricao());
            this.txtMarca.setText(this.veiculo.getMarcar());
            this.txtModelo.setText(this.veiculo.getModelo());
            // repasso o foco para o campo placa
            this.txtPlaca.requestFocus();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void indicarVeiculo()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por receber um objeto do tipo reserva selecionado pelo
     * usuario
     *
     * @param reserva recebe como parametro um objeto do tipo reserva e seta
     * para o formulario
     */
    public void indicarReserva(Reserva reserva) {
        try {

            //inicializo um objeto do tipo reserva
            this.reserva = reserva;
            //inicializo o objeto do tipo cliente
            this.cliente = reserva.getCliente();
            //inicializo o objeto do tipo veiculo
            this.veiculo = reserva.getVeiculo();
            //inicializo o objeto do tipo vaga
            this.vaga = reserva.getVaga();
            // seto o valor calculado para o total de horas
            this.txtTotalHoras.setText(conversorData.getTotalHrs().replace(",", "."));
            //seto os dados para o formulario 
            this.txtCodigoCliente.setText(String.valueOf(this.reserva.getCliente().getCodigo()));
            this.txtNomeCliente.setText(this.reserva.getCliente().getNome());
            this.txtCPFCNPJ.setText(this.reserva.getCliente().getCpf());
            this.txtTelefone.setText(this.reserva.getCliente().getTelefone());
            this.txtCelular.setText(this.reserva.getCliente().getCelular());
            this.txtCodigoVeiculo.setText(String.valueOf(this.reserva.getVeiculo().getCodigo()));
            this.txtDescricaoVeiculo.setText(this.reserva.getVeiculo().getDescricao());
            this.txtMarca.setText(this.reserva.getVeiculo().getMarcar());
            this.txtModelo.setText(this.reserva.getVeiculo().getModelo());
            this.txtPlaca.setText(this.reserva.getPlaca());
            this.txtCor.setText(this.reserva.getCor());
            this.txtCodigoVaga.setText(String.valueOf(this.reserva.getVaga().getCodigo()));
            this.txtReferenciaVaga.setText(this.reserva.getVaga().getReferencia());
            this.txtNumeroVaga.setText(String.valueOf(this.reserva.getVaga().getNumeroVaga()));
            this.txtDataEntrada.setText(conversorData.converteDataString(this.reserva.getDataEntrada()));
            this.txtHoraEntrada.setText(conversorData.converterHoraString(this.reserva.getHorarioinicio()));
            this.txtDataSaida.setText(conversorData.converteDataString(this.reserva.getDateSaida()));
            this.txtHoraSaida.setText(conversorData.converterHoraString(this.reserva.getHorariofinal()));
            this.txtValorHora.setText(String.valueOf(this.reserva.getValorHora()));
            this.txtTotalHoras.setText(String.valueOf(this.reserva.getTotalhoras()));
            this.txtTotal.setText(String.valueOf(this.reserva.getTotalReserva()));
            this.txtAreaObservacao.append(this.reserva.getObservacao());
            if (this.reserva.getSituacao() == 'A') {
                this.lblSituacao.setText("RESERVA : ABERTO");
                this.lblSituacao.setFont(new Font("tahoma", Font.BOLD, 12));
                this.lblSituacao.setForeground(Color.GREEN);
            } else {
                this.editavel(false);
                this.lblSituacao.setText("RESERVA : FECHADO");
                this.lblSituacao.setFont(new Font("tahoma", Font.BOLD, 12));
                this.lblSituacao.setForeground(Color.red);
            }
            this.painelDadosReserva.repaint();
            this.painelDadosReserva.revalidate();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void indicarReserva()");
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
    public void botoesEditaveis(boolean visivel) {
        try {
            this.btnSalvar.setEnabled(visivel);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void botoesEditaveis()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por limpar os dados contidos no formulario de reserva
     */
    private void limparCampos() {
        try {
            this.txtCodigoCliente.setText("");
            this.txtNomeCliente.setText("");
            this.txtCPFCNPJ.setText("");
            this.txtTelefone.setText("");
            this.txtCelular.setText("");
            this.txtCodigoVeiculo.setText("");
            this.txtDescricaoVeiculo.setText("");
            this.txtMarca.setText("");
            this.txtModelo.setText("");
            this.txtPlaca.setText("");
            this.txtCor.setText("");
            this.txtCodigoVaga.setText("");
            this.txtReferenciaVaga.setText("");
            this.txtNumeroVaga.setText("");
            this.txtDataEntrada.setText("");
            this.txtHoraEntrada.setText("");
            this.txtDataSaida.setText("");
            this.txtHoraSaida.setText("");
            this.txtAreaObservacao.setText("");
            this.txtValorHora.setText(String.valueOf(BigDecimal.ZERO));
            this.txtTotalHoras.setText(String.valueOf(BigDecimal.ZERO));
            this.txtTotal.setText(String.valueOf(BigDecimal.ZERO));
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void limparCampos()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por tornar ou nao editival os campos do formulario
     *
     * @param editavel recebe como parametro um dado booleano informando se
     * estará ou nao editival
     */
    public void editavel(boolean editavel) {
        try {
            this.txtCodigoCliente.setEditable(editavel);
            this.txtCodigoVaga.setEditable(editavel);
            this.txtCodigoVeiculo.setEditable(editavel);
            this.txtValorHora.setEditable(editavel);
            this.txtAreaObservacao.setEditable(editavel);
            this.painelvagas.setEnabled(editavel);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " public void editavel()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo responsavel por tornar ou nao editavel os botoes finalizar e
     * imprimir
     *
     * @param finalizar recebe como parametro um objeto do tipo booleano
     * informado se estara ou nao habilitado
     */
    public void finalizacao(boolean finalizar) {
        try {
            this.btnFinalizar.setEnabled(finalizar);
            this.btnImprimir.setEnabled(finalizar);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "public void finalizacao()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem como funcao verificar se as informaçoes basicas do
     * formulario estao preenchidas
     *
     * @return um dado booleano, sendo verdadeiro se todas as informaçoes
     * estiverem prenchidas e falso se nao
     */
    private boolean validarSalvar() {
        try {
            // se o campo codigo do cliente estiver vazio
            if (this.txtCodigoCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe um cliente para a reserva!");
                this.txtCodigoCliente.requestFocus();
                return false;
                // se o campo codigo do veiculo estiver vazio
            } else if (this.txtCodigoVeiculo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe um veiculo para a reserva!");
                this.txtCodigoVeiculo.requestFocus();
                return false;
                // se o campo placa estiver vazio
            } else if (this.txtPlaca.getText().equals("   -    ")) {
                JOptionPane.showMessageDialog(this, "Informe a placa do veiculo!");
                this.txtPlaca.requestFocus();
                return false;
                // se o campo cor estiver vazio
            } else if (this.txtCor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a cor do veiculo!");
                this.txtCor.requestFocus();
                return false;
                // se o campo codigo vaga estiver vazio 
            } else if (this.txtCodigoVaga.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a vaga para a reserva!");
                this.painelvagas.requestFocus();
                return false;
                // se o campo data entrada for vazio
            } else if (this.txtDataEntrada.getText().equals("  /  /    ")) {
                JOptionPane.showMessageDialog(this, "Informe a data de entrada !");
                this.txtDataEntrada.requestFocus();
                return false;
                // se o campo horario entrada for vazio
            } else if (this.txtHoraEntrada.getText().equals("  :  :  ")) {
                JOptionPane.showMessageDialog(this, "Informe a hora da entrada!");
                this.txtDataEntrada.requestFocus();
                return false;
            } else if (this.txtValorHora.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o valor da hora para a reserva!");
                this.txtValorHora.requestFocus();
                return false;
            } else if (Double.parseDouble(txtValorHora.getText().replace(",", ".")) == 0) {
                JOptionPane.showMessageDialog(this, "Informe o valor da hora para a reserva!");
                this.txtValorHora.setSelectionStart(0);
                this.txtValorHora.setSelectionEnd(txtValorHora.getText().length());
                this.txtValorHora.requestFocus();
                return false;
            } else if (txtPlaca.getText().trim().length() < 8) {
                JOptionPane.showMessageDialog(this, "Informe a placa do veículo!");
                this.txtPlaca.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private boolean validarSalvar()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
        return true;
    }

    /**
     *
     * @param cliente
     * @param vaga
     * @param veiculo
     * @param placa
     * @param cor
     * @param dataEntrada
     * @param horaEntrada
     * @param dataSaida
     * @param horaSaida
     * @param totalhoras
     * @param valorHora
     * @param total
     */
    private void inserirReserva(Cliente cliente, Vaga vaga, Veiculo veiculo, String placa, String cor, String dataEntrada, String horaEntrada, String dataSaida, String horaSaida, String totalhoras, String valorHora, String total, String observacao) {
        try {
            // seto os dados do parametro para os atributos do objeto reserva
            this.reserva.setCliente(cliente);
            this.reserva.setVaga(vaga);
            this.reserva.setVeiculo(veiculo);
            this.reserva.setPlaca(placa);
            this.reserva.setCor(cor);
            this.reserva.setDataEntrada(conversorData.converterStringData(dataEntrada));
            this.reserva.setDateSaida(conversorData.converterStringData(dataSaida));
            this.reserva.setHorariofinal(conversorData.converterStringHora(horaSaida));
            this.reserva.setHorarioinicio(conversorData.converterStringHora(horaEntrada));
            String valorTemp = total.replace(".", "");
            this.reserva.setTotalReserva(new BigDecimal(valorTemp.replace(",", ".")));
            this.reserva.setTotalhoras(new BigDecimal(totalhoras.replace(",", ".")));
            this.reserva.setValorHora(new BigDecimal(valorHora.replace(",", ".")));
            this.reserva.setObservacao(observacao.trim());
            this.reserva.setSituacao('F');
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void inserirReserva()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     *
     * @param cliente
     * @param vaga
     * @param veiculo
     * @param placa
     * @param cor
     * @param dataEntrada
     * @param horaEntrada
     */
    private void inserirReserva(Cliente cliente, Vaga vaga, Veiculo veiculo, String placa, String cor,
            String dataEntrada, String horaEntrada, String dataSaida, String horaSaida, String valorHora, String Observacao) {
        try {
            // seto os dados do parametro para os atributos do objeto reserva
            this.reserva.setCliente(cliente);
            this.reserva.setVaga(vaga);
            this.reserva.setVeiculo(veiculo);
            this.reserva.setPlaca(placa);
            this.reserva.setCor(cor);
            this.reserva.setDataEntrada(conversorData.converterStringData(dataEntrada));
            this.reserva.setHorarioinicio(conversorData.converterStringHora(horaEntrada));
            this.reserva.setHorariofinal(conversorData.converterStringHora(horaSaida));
            this.reserva.setDateSaida(conversorData.converterStringData(dataSaida));
            this.reserva.setValorHora(new BigDecimal(valorHora.replace(",", ".")));
            this.reserva.setObservacao(Observacao);
            this.reserva.setTotalhoras(new BigDecimal(conversorData.getTotalHrs().replace(",", ".")));
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void inserirReserva()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem como função calcular o tempo de duração de uma locação
     *
     * @param dataEntrada recebe como parametro a data informada em formato
     * string no campo dataentrada
     * @param dataSaida recebe como parametro a data informada em formato string
     * no campo datasaida
     */
    private void calcularTotalHoras(String dataEntrada, String dataSaida) {
        try {
            //chamada ao metodo responsavel por calcular o total de horas da locação de uma reserva
            this.conversorData.calcularTotalHoras(dataEntrada, dataSaida);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void calcularTotalHoras()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    /**
     * metodo que tem a responsabilidade de calcular o total da reserva e
     * atribuir ao campo do formulario
     */
    private void calcularTotalReserva() {
        try {
            this.txtTotal.setText(String.valueOf(conversorData.getDecimalFormat().format((Double.parseDouble(txtValorHora.getText().replace(",", ".")) * Double.valueOf(txtTotalHoras.getText().replace(",", "."))))));
            this.reserva.setTotalReserva(new BigDecimal(this.txtTotal.getText().replace(".", "").replace(",", ".")));
        } catch (Exception ex) {
            ex.printStackTrace();
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void calcularTotalReserva()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundoReserva = new javax.swing.JPanel();
        ToolBarSuperior2 = new javax.swing.JToolBar();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        painelFundoVagas = new javax.swing.JPanel();
        painelvagas = new javax.swing.JPanel();
        lblVagasTotais = new javax.swing.JLabel();
        lblVagasOcupadas = new javax.swing.JLabel();
        painelDadosReserva = new javax.swing.JPanel();
        txtCodigoCliente = new javax.swing.JFormattedTextField();
        txtNomeCliente = new javax.swing.JFormattedTextField();
        txtCPFCNPJ = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCodigoVeiculo = new javax.swing.JFormattedTextField();
        txtDescricaoVeiculo = new javax.swing.JFormattedTextField();
        txtMarca = new javax.swing.JFormattedTextField();
        txtModelo = new javax.swing.JFormattedTextField();
        txtCor = new javax.swing.JFormattedTextField();
        txtPlaca = new javax.swing.JFormattedTextField();
        txtCodigoVaga = new javax.swing.JFormattedTextField();
        txtReferenciaVaga = new javax.swing.JFormattedTextField();
        txtNumeroVaga = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JFormattedTextField();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        lblCodigoCliente = new javax.swing.JLabel();
        lblCpfCnpj = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        lblCodigoVeiculo = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        lblCor = new javax.swing.JLabel();
        lblCodigoVaga = new javax.swing.JLabel();
        lblNumeroVaga = new javax.swing.JLabel();
        txtDataSaida = new javax.swing.JFormattedTextField();
        txtHoraEntrada = new javax.swing.JFormattedTextField();
        txtHoraSaida = new javax.swing.JFormattedTextField();
        txtValorHora = new javax.swing.JFormattedTextField();
        lblImagem = new javax.swing.JLabel();
        lblDataEntrada = new javax.swing.JLabel();
        lblHoraEntrada = new javax.swing.JLabel();
        lblDataSaida = new javax.swing.JLabel();
        lblHoraSaida = new javax.swing.JLabel();
        lblValorHora = new javax.swing.JLabel();
        lblTotalReserva = new javax.swing.JLabel();
        btnCadastrarVeiculo = new javax.swing.JButton();
        btnCadastrarVaga = new javax.swing.JButton();
        btnCadastrarCliente = new javax.swing.JButton();
        txtTotalHoras = new javax.swing.JFormattedTextField();
        lblTotalHoras = new javax.swing.JLabel();
        lblSituacao = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        txtAreaObservacao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PARKING MANAGEMENT SYSTEM - Cadastro de Reserva");

        painelFundoReserva.setBackground(new java.awt.Color(255, 255, 255));

        ToolBarSuperior2.setBackground(new java.awt.Color(255, 255, 0));
        ToolBarSuperior2.setRollover(true);

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/ALTERAR_INTERNALFRAME.png"))); // NOI18N
        btnAlterar.setText("ALTERAR");
        btnAlterar.setFocusable(false);
        btnAlterar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        ToolBarSuperior2.add(btnAlterar);

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
        ToolBarSuperior2.add(btnNovo);

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
        ToolBarSuperior2.add(btnPesquisar);

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
        ToolBarSuperior2.add(btnSalvar);

        btnFinalizar.setBackground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/FINALIZAR_INTERNALFRAME.png"))); // NOI18N
        btnFinalizar.setText("FINALIZAR");
        btnFinalizar.setEnabled(false);
        btnFinalizar.setFocusable(false);
        btnFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        ToolBarSuperior2.add(btnFinalizar);

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/IMPRIMIR_INTERNALFRAME.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setEnabled(false);
        btnImprimir.setFocusable(false);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        ToolBarSuperior2.add(btnImprimir);

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
        ToolBarSuperior2.add(btnSair);

        painelFundoVagas.setBackground(new java.awt.Color(255, 255, 255));
        painelFundoVagas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estacionamento ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        painelFundoVagas.setPreferredSize(new java.awt.Dimension(802, 652));

        painelvagas.setBackground(new java.awt.Color(255, 255, 255));
        painelvagas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vagas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        painelvagas.setPreferredSize(new java.awt.Dimension(760, 204));
        painelvagas.setLayout(new GridLayout(2,2,5,5));
        painelvagas.setLayout(new java.awt.GridLayout(5, 0));

        lblVagasTotais.setBackground(new java.awt.Color(255, 255, 255));

        lblVagasOcupadas.setBackground(new java.awt.Color(255, 255, 255));

        painelDadosReserva.setBackground(new java.awt.Color(255, 255, 255));
        painelDadosReserva.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Reserva", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtCodigoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyTyped(evt);
            }
        });

        txtNomeCliente.setEditable(false);
        txtNomeCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNomeCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNomeCliente.setEnabled(true);

        txtCPFCNPJ.setEditable(false);
        txtCPFCNPJ.setBackground(new java.awt.Color(255, 255, 255));
        txtCPFCNPJ.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCelular.setEditable(false);
        txtCelular.setBackground(new java.awt.Color(255, 255, 255));
        txtCelular.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTelefone.setEditable(false);
        txtTelefone.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefone.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCodigoVeiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoVeiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVeiculoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVeiculoKeyTyped(evt);
            }
        });

        txtDescricaoVeiculo.setEditable(false);
        txtDescricaoVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        txtDescricaoVeiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMarca.setEditable(false);
        txtMarca.setBackground(new java.awt.Color(255, 255, 255));
        txtMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtModelo.setEditable(false);
        txtModelo.setBackground(new java.awt.Color(255, 255, 255));
        txtModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorKeyTyped(evt);
            }
        });

        try {
            txtPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlacaKeyPressed(evt);
            }
        });

        txtCodigoVaga.setEditable(false);
        txtCodigoVaga.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoVaga.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCodigoVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtReferenciaVaga.setEditable(false);
        txtReferenciaVaga.setBackground(new java.awt.Color(255, 255, 255));
        txtReferenciaVaga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNumeroVaga.setEditable(false);
        txtNumeroVaga.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroVaga.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNumeroVaga.setForeground(new java.awt.Color(255, 0, 51));
        txtNumeroVaga.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTotal.setText(String.valueOf(BigDecimal.ZERO));
        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDataEntrada.setEditable(false);
        txtDataEntrada.setBackground(new java.awt.Color(255, 255, 255));
        txtDataEntrada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        try {
            txtDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblCodigoCliente.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigoCliente.setText(" Código - (F2 - Consultar ) Cliente");

        lblCpfCnpj.setBackground(new java.awt.Color(255, 255, 255));
        lblCpfCnpj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCpfCnpj.setText("CPF/CNPJ");

        lblTelefone.setBackground(new java.awt.Color(255, 255, 255));
        lblTelefone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTelefone.setText("Telefone");

        lblCelular.setBackground(new java.awt.Color(255, 255, 255));
        lblCelular.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCelular.setText("Celular");

        lblCodigoVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigoVeiculo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigoVeiculo.setText(" Código - (F2 - Consultar ) Veículo");

        lblMarca.setBackground(new java.awt.Color(255, 255, 255));
        lblMarca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMarca.setText("Marca");

        lblModelo.setBackground(new java.awt.Color(255, 255, 255));
        lblModelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblModelo.setText("Modelo");

        lblPlaca.setBackground(new java.awt.Color(255, 255, 255));
        lblPlaca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPlaca.setText("Placa");

        lblCor.setBackground(new java.awt.Color(255, 255, 255));
        lblCor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCor.setText("Cor");
        lblCor.setToolTipText("");

        lblCodigoVaga.setBackground(new java.awt.Color(255, 255, 255));
        lblCodigoVaga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCodigoVaga.setText(" Código -  Vaga    Referência");

        lblNumeroVaga.setBackground(new java.awt.Color(255, 255, 255));
        lblNumeroVaga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNumeroVaga.setText("Número Vaga");

        txtDataSaida.setEditable(false);
        txtDataSaida.setBackground(new java.awt.Color(255, 255, 255));
        txtDataSaida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        try {
            txtDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtHoraEntrada.setEditable(false);
        txtHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        txtHoraEntrada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        try {
            txtHoraEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtHoraSaida.setEditable(false);
        txtHoraSaida.setBackground(new java.awt.Color(255, 255, 255));
        try {
            txtHoraSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtValorHora.setText(String.valueOf(BigDecimal.ZERO));
        txtValorHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorHoraKeyTyped(evt);
            }
        });

        lblImagem.setBackground(new java.awt.Color(255, 255, 255));
        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/reserva.png"))); // NOI18N

        lblDataEntrada.setBackground(new java.awt.Color(255, 255, 255));
        lblDataEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDataEntrada.setText("Data Entrada");

        lblHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        lblHoraEntrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblHoraEntrada.setText("Hora Entrada");

        lblDataSaida.setBackground(new java.awt.Color(255, 255, 255));
        lblDataSaida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDataSaida.setText("Data Saída");

        lblHoraSaida.setBackground(new java.awt.Color(255, 255, 255));
        lblHoraSaida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblHoraSaida.setText("Hora Saída");

        lblValorHora.setBackground(new java.awt.Color(255, 255, 255));
        lblValorHora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblValorHora.setText("Valor(R$)");

        lblTotalReserva.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalReserva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotalReserva.setText("Total");

        btnCadastrarVeiculo.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastrarVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/VEICULO_SMALL.png"))); // NOI18N
        btnCadastrarVeiculo.setToolTipText("Acesso o cadastro de veículos");
        btnCadastrarVeiculo.setBorder(null);
        btnCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarVeiculoActionPerformed(evt);
            }
        });
        btnCadastrarVeiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastrarVeiculoKeyPressed(evt);
            }
        });

        btnCadastrarVaga.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastrarVaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/VAGA_SMALL.png"))); // NOI18N
        btnCadastrarVaga.setToolTipText("Acesse o cadastro de vagas");
        btnCadastrarVaga.setBorder(null);
        btnCadastrarVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarVagaActionPerformed(evt);
            }
        });
        btnCadastrarVaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastrarVagaKeyPressed(evt);
            }
        });

        btnCadastrarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/parking/imagens/CLIENTE_SMALL.png"))); // NOI18N
        btnCadastrarCliente.setToolTipText("Acesso  o cadaastro de clientes");
        btnCadastrarCliente.setBorder(null);
        btnCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClienteActionPerformed(evt);
            }
        });
        btnCadastrarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastrarClienteKeyPressed(evt);
            }
        });

        txtTotalHoras.setText(String.valueOf(BigDecimal.ZERO));
        txtTotalHoras.setEditable(false);
        txtTotalHoras.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotalHoras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalHorasKeyTyped(evt);
            }
        });

        lblTotalHoras.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalHoras.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotalHoras.setText("Total Hr");

        lblSituacao.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout painelDadosReservaLayout = new javax.swing.GroupLayout(painelDadosReserva);
        painelDadosReserva.setLayout(painelDadosReservaLayout);
        painelDadosReservaLayout.setHorizontalGroup(
            painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosReservaLayout.createSequentialGroup()
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addComponent(lblCodigoCliente)
                                .addGap(139, 139, 139)
                                .addComponent(lblCpfCnpj)
                                .addGap(67, 67, 67)
                                .addComponent(lblTelefone)
                                .addGap(71, 71, 71)
                                .addComponent(lblCelular))
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addComponent(txtCodigoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtDescricaoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblCodigoVeiculo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMarca)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblModelo)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPlaca)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCor))
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCodigoVaga)
                                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblDataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtCodigoVaga))
                                                .addGap(10, 10, 10)
                                                .addComponent(txtReferenciaVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNumeroVaga)
                                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                                .addComponent(txtNumeroVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCadastrarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                                .addComponent(lblHoraEntrada)
                                                .addGap(32, 32, 32)
                                                .addComponent(lblDataSaida)
                                                .addGap(43, 43, 43)
                                                .addComponent(lblHoraSaida))
                                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                                .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTotalHoras))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTotalReserva)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblSituacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblImagem)))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosReservaLayout.createSequentialGroup()
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCadastrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(37, 37, 37))))
        );
        painelDadosReservaLayout.setVerticalGroup(
            painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoCliente)
                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTelefone)
                                .addComponent(lblCpfCnpj))
                            .addComponent(lblCelular))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(lblCodigoVeiculo))
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblPlaca)
                                            .addComponent(lblCor))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lblMarca))
                                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblModelo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDescricaoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosReservaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCadastrarVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosReservaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigoVaga)
                            .addComponent(lblNumeroVaga))
                        .addGap(6, 6, 6)
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSituacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigoVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtReferenciaVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumeroVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCadastrarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHoraEntrada)
                                .addComponent(lblDataEntrada)
                                .addComponent(lblDataSaida))
                            .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHoraSaida)
                                .addComponent(lblTotalHoras))
                            .addGroup(painelDadosReservaLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblValorHora)
                                    .addComponent(lblTotalReserva))))
                        .addGap(5, 5, 5)
                        .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelDadosReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosReservaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImagem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAreaObservacao.setColumns(20);
        txtAreaObservacao.setRows(5);
        txtAreaObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scroll.setViewportView(txtAreaObservacao);

        javax.swing.GroupLayout painelFundoVagasLayout = new javax.swing.GroupLayout(painelFundoVagas);
        painelFundoVagas.setLayout(painelFundoVagasLayout);
        painelFundoVagasLayout.setHorizontalGroup(
            painelFundoVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoVagasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVagasTotais, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblVagasOcupadas, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(painelvagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll)
            .addComponent(painelDadosReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelFundoVagasLayout.setVerticalGroup(
            painelFundoVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoVagasLayout.createSequentialGroup()
                .addGroup(painelFundoVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblVagasTotais, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(lblVagasOcupadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelvagas, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDadosReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout painelFundoReservaLayout = new javax.swing.GroupLayout(painelFundoReserva);
        painelFundoReserva.setLayout(painelFundoReservaLayout);
        painelFundoReservaLayout.setHorizontalGroup(
            painelFundoReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBarSuperior2, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addComponent(painelFundoVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelFundoReservaLayout.setVerticalGroup(
            painelFundoReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoReservaLayout.createSequentialGroup()
                .addComponent(ToolBarSuperior2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelFundoVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        try {
            this.botoesEditaveis(true);
            this.habilitarFormulario(true);
            this.editavel(true);
            if (this.reserva.getSituacao() == 'F') {
                this.btnImprimir.setEnabled(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnAlterarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            //habilito os campos do formulario
            this.habilitarFormulario(true);
            //habilito os botoes da paleta de cadastro
            this.botoesEditaveis(true);
            //limpo os campso
            this.limparCampos();
            //chamada ao metodo responsavel por setar a data e hora de entrada
            this.carregarDataEntrada();
            // seto o texto para o label
            this.lblSituacao.setText("RESERVA : ABERTO");
            this.lblSituacao.setFont(new Font("tahoma", Font.BOLD, 12));
            this.lblSituacao.setForeground(Color.GREEN);
            //torno editavel os botoes do formulario
            this.editavel(true);
            reserva = new Reserva();
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnNovoActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        try {
            //limpo os campos do formulario
            this.limparCampos();
            //instancio a tela de cadastro de reserva
            this.telaConsultaReserva = new TelaConsultaReserva(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaConsultaReserva.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaConsultaReserva.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void btnPesquisarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            this.carregarDataSaida();
            // se todos os dados estiverem devidamente preenchidos
            if (validarSalvar()) {
                this.vaga.setStatus(false);
                // insiro os dados na reserva
                this.inserirReserva(cliente, vaga, veiculo, txtPlaca.getText(), txtCor.getText(), txtDataEntrada.getText(),
                        txtHoraEntrada.getText(), txtDataSaida.getText(), txtHoraSaida.getText(), txtValorHora.getText(), txtAreaObservacao.getText());
                // se o objeto reserva ainda nao foi persistido
                if (reserva.getCodigo() == null) {
                    if (!txtPlaca.getText().isEmpty()) {
                        Reserva r = this.persistencia.retornarPlacaCadastrada(txtPlaca.getText(), reserva.getCodigo());
                        if (r == null) {
                            //atualizo o objeto vaga
                            persistencia.atualizarObjeto(vaga);
                            // insiro o objeto no banco de dados
                            persistencia.inserirObjeto(reserva);
                            JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
                            this.botoesEditaveis(false);
                            this.limparCampos();
                            this.habilitarFormulario(false);
                            //redesenho o painel de vagas
                            this.removerVagas();
                            this.carregarQuantidadeVagasCadastradas();
                            //habilito o botao finalizar
                            btnFinalizar.setEnabled(false);
                            btnImprimir.setEnabled(false);
                            //reinicio o objeto do tipo reserva
                            this.reserva = null;
                            //inicializo o objeto reserva
                            this.reserva = new Reserva();
                        } else {
                            int resposta = JOptionPane.showConfirmDialog(this, "Placa já vinculada à outra reserva com situação aberta. Deseja visulizar ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                            if (resposta == JOptionPane.YES_OPTION) {
                                this.reserva = r;
                                indicarReserva(reserva);
                            } else if (resposta == JOptionPane.NO_OPTION) {
                                this.txtPlaca.requestFocus();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Informe a placa do veículo !");
                    }
                } else {
                    if (!txtPlaca.getText().isEmpty()) {
                        Reserva r = this.persistencia.retornarPlacaCadastrada(txtPlaca.getText(), reserva.getCodigo());
                        if (r == null) {
                            //atualizo o objeto vaga
                            persistencia.atualizarObjeto(vaga);
                            // atualizo o objeto no banco de dados
                            persistencia.atualizarObjeto(reserva);
                            JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
                            this.botoesEditaveis(false);
                            this.limparCampos();
                            this.habilitarFormulario(false);
                            //redesenho o painel de vagas
                            this.removerVagas();
                            this.carregarQuantidadeVagasCadastradas();
                            //habilito o botao finalizar
                            btnFinalizar.setEnabled(false);
                            btnImprimir.setEnabled(false);
                            //reinicio o objeto do tipo reserva
                            this.reserva = null;
                            //inicializo o objeto reserva
                            this.reserva = new Reserva();
                        } else {
                            int resposta = JOptionPane.showConfirmDialog(this, "Placa já vinculada à outra reserva com situação aberta. Deseja visulizar ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                            if (resposta == JOptionPane.YES_OPTION) {
                                this.reserva = r;
                                indicarReserva(reserva);
                            } else if (resposta == JOptionPane.NO_OPTION) {
                                this.txtPlaca.requestFocus();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Informe a placa do veículo !");
                    }
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnSalvarActionPerformed()");
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
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnSairActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }

    }//GEN-LAST:event_btnSairActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            this.carregarDataSaida();
            this.calcularTotalReserva();
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente finalizar a reserva ?", "Confirmação", JOptionPane.YES_NO_OPTION);
            // se a resposta do usuario for sim 
            if (resposta == JOptionPane.YES_OPTION) {
                if (validarSalvar()) {
                    //JOptionPane.showMessageDialog(this,Double.parseDouble(txtTotal.getText().replace(",", ".")));
                    if (Double.parseDouble(txtTotal.getText().replace(".", "").replace(",", ".")) == 0) {
                        int resp = JOptionPane.showConfirmDialog(this, "A reserva está com valor zerado. Deseja realmente finaliza-la?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        // se a resposta do usuario for sim 
                        if (resp == JOptionPane.YES_OPTION) {
                            //chamada ao metodo responsavel por inserir os dados no objeto reserva
                            inserirReserva(cliente, vaga, veiculo, txtPlaca.getText(), txtCor.getText(), txtDataEntrada.getText(), txtHoraEntrada.getText(), txtDataSaida.getText(), txtHoraSaida.getText(), txtTotalHoras.getText(), txtValorHora.getText(), txtTotal.getText(), txtAreaObservacao.getText());
                            //atualizo o objeto no banco de dados
                            this.persistencia.atualizarObjeto(reserva);
                            //limpo os campos do formulario
                            this.limparCampos();
                            //desabilito a visualizacao do formulario
                            this.habilitarFormulario(false);
                            //altero a exibição dos botoes do formulario
                            this.botoesEditaveis(false);
                            //altero o status da vaga
                            this.vaga.setStatus(true);
                            this.persistencia.atualizarObjeto(vaga);
                            //removo as vagas do painel
                            this.removerVagas();
                            //recarrego as vagas para o painel
                            this.carregarQuantidadeVagasCadastradas();
                            //reinicio o objeto do tipo reserva
                            this.reserva = null;
                            //inicializo o objeto reserva
                            this.reserva = new Reserva();
                            //desabilito os botoes
                            this.btnImprimir.setEnabled(false);
                            this.btnFinalizar.setEnabled(false);
                        } else {
                            txtValorHora.requestFocus();
                        }
                    } else {
                        //chamada ao metodo responsavel por inserir os dados no objeto reserva
                        inserirReserva(cliente, vaga, veiculo, txtPlaca.getText(), txtCor.getText(), txtDataEntrada.getText(), txtHoraEntrada.getText(), txtDataSaida.getText(), txtHoraSaida.getText(), txtTotalHoras.getText(), txtValorHora.getText(), txtTotal.getText(), txtAreaObservacao.getText());
                        //atualizo o objeto no banco de dados
                        this.persistencia.atualizarObjeto(reserva);
                        //limpo os campos do formulario
                        this.limparCampos();
                        //desabilito a visualizacao do formulario
                        this.habilitarFormulario(false);
                        //altero a exibição dos botoes do formulario
                        this.botoesEditaveis(false);
                        //altero o status da vaga
                        this.vaga.setStatus(true);
                        this.persistencia.atualizarObjeto(vaga);
                        //removo as vagas do painel
                        this.removerVagas();
                        //recarrego as vagas para o painel
                        this.carregarQuantidadeVagasCadastradas();
                        //reinicio o objeto do tipo reserva
                        this.reserva = null;
                        //inicializo o objeto reserva
                        this.reserva = new Reserva();
                        //desabilito os botoes
                        this.btnImprimir.setEnabled(false);
                        this.btnFinalizar.setEnabled(false);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnFinalizarActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        } finally {
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente imprimir o comprovante ?", "Confirmação", JOptionPane.YES_NO_OPTION);
            // se a responsta for sim
            if (resposta == JOptionPane.YES_OPTION) {
                this.botoesEditaveis(false);
                this.habilitarFormulario(false);
                this.repaint();
                this.revalidate();
                this.geradorRelatorio = new GeradorRelatorio(fileRead.getImpressoes());
                if (geradorRelatorio.existenciaRelatorio()) {
                    //chamado a metodo responsavel por gerar o comprovante de locação
                    this.geradorRelatorio.gerarComprovante(persistencia.imprimirComporvanteReserva(this.reserva.getCodigo()));
                    this.visualizacaoRelatorio.setSize(832, 700);
                    this.visualizacaoRelatorio.setLocationRelativeTo(null);
                    this.visualizacaoRelatorio.getContentPane().add(this.geradorRelatorio.getJasperViewer().getContentPane());
                    this.visualizacaoRelatorio.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "O caminho informado para o relatório é inválido ou está em branco.Verifique!");
                }
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnImprimirActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyTyped
        try {
            String numeros = "0123456789";
            // se a tecla pressionada nao for um numero
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                // elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void txtCodigoClienteKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCodigoClienteKeyTyped

    private void txtCodigoVeiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVeiculoKeyTyped
        try {
            String numeros = "0123456789";
            // se a tecla pressionada nao for um numero
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                // elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void txtCodigoVeiculoKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCodigoVeiculoKeyTyped

    private void txtValorHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorHoraKeyTyped
        try {
            String numeros = "0123456789.,";
            // se a tecla pressionada nao for um numero
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                // elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void txtValorHoraKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtValorHoraKeyTyped

    private void btnCadastrarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarVeiculoActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaVeiculo = new TelaVeiculo(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaVeiculo.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaVeiculo.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnCadastrarVeiculoActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarVeiculoActionPerformed

    private void btnCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClienteActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaCliente = new TelaCliente(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaCliente.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaCliente.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnCadastrarClienteActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarClienteActionPerformed

    private void btnCadastrarVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarVagaActionPerformed
        try {
            //instancio a tela de cadastro de clientes
            this.telaVaga = new TelaVaga(telaPrincipal, true, persistencia, this);
            //centralizo a exibicao do componente no JFrame
            this.telaVaga.setLocationRelativeTo(null);
            //torno visivel a tela de cadastro de clientes
            this.telaVaga.setVisible(true);
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void btnCadastrarVagaActionPerformed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarVagaActionPerformed

    private void btnCadastrarVagaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarVagaKeyPressed
        try {
            // se a tecla pressionada for o enter
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                //instancio a tela de cadastro de clientes
                this.telaVaga = new TelaVaga(telaPrincipal, true, persistencia, this);
                //centralizo a exibicao do componente no JFrame
                this.telaVaga.setLocationRelativeTo(null);
                //torno visivel a tela de cadastro de clientes
                this.telaVaga.setVisible(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnCadastrarVagaKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarVagaKeyPressed

    private void btnCadastrarVeiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarVeiculoKeyPressed
        try {
            // se a tecla pressionada for o enter
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                //instancio a tela de cadastro de clientes
                this.telaVeiculo = new TelaVeiculo(telaPrincipal, true, persistencia, this);
                //centralizo a exibicao do componente no JFrame
                this.telaVeiculo.setLocationRelativeTo(null);
                //torno visivel a tela de cadastro de clientes
                this.telaVeiculo.setVisible(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnCadastrarVeiculoKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarVeiculoKeyPressed

    private void btnCadastrarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarClienteKeyPressed
        try {
            //se a tecla pressionada for o enter
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                //instancio a tela de cadastro de clientes
                this.telaCliente = new TelaCliente(telaPrincipal, true, persistencia, this);
                //centralizo a exibicao do componente no JFrame
                this.telaCliente.setLocationRelativeTo(null);
                //torno visivel a tela de cadastro de clientes
                this.telaCliente.setVisible(true);
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void btnCadastrarClienteKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_btnCadastrarClienteKeyPressed

    private void txtTotalHorasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalHorasKeyTyped
        try {
            String numeros = "0123456789,.";
            // se a tecla informada nao estiver contida na string
            if (!numeros.contains(String.valueOf(evt.getKeyChar()))) {
                //elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void txtTotalHorasKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtTotalHorasKeyTyped

    private void txtCodigoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyPressed
        try {
            // se a tecla pressionada for igual ao F2
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
                //instancio a tela de cadastro de clientes
                this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
                //centralizo a exibicao do componente no JFrame
                this.telaConsultaCliente.setLocationRelativeTo(null);
                //torno visivel a tela de cadastro de clientes
                this.telaConsultaCliente.setVisible(true);
                // se a tecla pressionada for o enter
            } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // se o campo codigo for vazio
                if (!txtCodigoCliente.getText().isEmpty()) {
                    //retorno da camada de persistencia uma lista de objetos contendo o codigo do cliente igual ao informado na no campo codigo cliente
                    ArrayList<Cliente> lista = this.persistencia.retornarListaCliente(0, txtCodigoCliente.getText());
                    // se a lista nao for vazia
                    if (lista.isEmpty()) {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoCliente.setText("");
                        this.repaint();
                        //instancio a tela de cadastro de clientes
                        this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
                        //centralizo a exibicao do componente no JFrame
                        this.telaConsultaCliente.setLocationRelativeTo(null);
                        //torno visivel a tela de cadastro de clientes
                        this.telaConsultaCliente.setVisible(true);
                    } else {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoCliente.setText("");
                        this.repaint();
                        //seto o objeto cliente selecionado
                        this.indicarCliente(lista.get(0));
                        //seto o foco para o campo codigo cliente
                        this.txtCodigoVeiculo.requestFocus();

                    }
                } else {
                    //instancio a tela de cadastro de clientes
                    this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
                    //centralizo a exibicao do componente no JFrame
                    this.telaConsultaCliente.setLocationRelativeTo(null);
                    //torno visivel a tela de cadastro de clientes
                    this.telaConsultaCliente.setVisible(true);
                    this.txtCodigoCliente.setText("");
                    this.repaint();
                }
                // se a tecla pressionada for o tab    
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                // se o campo codigo for vazio
                if (!txtCodigoCliente.getText().isEmpty()) {
                    //retorno da camada de persistencia uma lista de objetos contendo o codigo do cliente igual ao informado na no campo codigo cliente
                    ArrayList<Cliente> lista = this.persistencia.retornarListaCliente(0, txtCodigoCliente.getText());
                    // se a lista nao for vazia
                    if (lista.isEmpty()) {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoCliente.setText("");
                        this.repaint();
                        //instancio a tela de cadastro de clientes
                        this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
                        //centralizo a exibicao do componente no JFrame
                        this.telaConsultaCliente.setLocationRelativeTo(null);
                        //torno visivel a tela de cadastro de clientes
                        this.telaConsultaCliente.setVisible(true);
                    } else {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoCliente.setText("");
                        this.repaint();
                        //seto o objeto cliente selecionado
                        this.indicarCliente(lista.get(0));
                        //seto o foco para o campo codigo cliente
                        this.txtCodigoVeiculo.requestFocus();

                    }
                } else {
                    //instancio a tela de cadastro de clientes
                    this.telaConsultaCliente = new TelaConsultaCliente(telaPrincipal, true, persistencia, this);
                    //centralizo a exibicao do componente no JFrame
                    this.telaConsultaCliente.setLocationRelativeTo(null);
                    //torno visivel a tela de cadastro de clientes
                    this.telaConsultaCliente.setVisible(true);
                    this.txtCodigoCliente.setText("");
                    this.repaint();
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void txtCodigoClienteKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCodigoClienteKeyPressed

    private void txtCodigoVeiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVeiculoKeyPressed
        try {
            // se a tecla pressionada for igual ao F2
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
                //instancio a tela de cadastro de clientes
                this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
                //centralizo a exibicao do componente no JFrame
                this.telaConsultaVeiculo.setLocationRelativeTo(null);
                //torno visivel a tela de cadastro de clientes
                this.telaConsultaVeiculo.setVisible(true);
                // se a tecla pressionada for enter   
            } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                // se o campo codigo for vazio
                if (!txtCodigoVeiculo.getText().isEmpty()) {
                    //retorno da camada de persistencia uma lista de objetos contendo o codigo do cliente igual ao informado na no campo codigo cliente
                    ArrayList<Veiculo> lista = this.persistencia.retornarListaVeiculos(0, txtCodigoVeiculo.getText());
                    // se a lista nao for vazia
                    if (lista.isEmpty()) {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoVeiculo.setText("");
                        this.repaint();
                        //instancio a tela de cadastro de clientes
                        this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
                        //centralizo a exibicao do componente no JFrame
                        this.telaConsultaVeiculo.setLocationRelativeTo(null);
                        //torno visivel a tela de cadastro de clientes
                        this.telaConsultaVeiculo.setVisible(true);
                    } else {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoVeiculo.setText("");
                        this.repaint();
                        //seto o objeto cliente selecionado
                        this.indicarVeiculo(lista.get(0));
                        //seto o foco para o campo codigo cliente
                        this.txtPlaca.requestFocus();

                    }
                } else {
                    //instancio a tela de cadastro de clientes
                    this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
                    //centralizo a exibicao do componente no JFrame
                    this.telaConsultaVeiculo.setLocationRelativeTo(null);
                    //torno visivel a tela de cadastro de clientes
                    this.telaConsultaVeiculo.setVisible(true);
                    this.txtCodigoVeiculo.setText("");
                    this.repaint();
                }
                // se a tecla pressionada for o tab    
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                // se o campo codigo for vazio
                if (!txtCodigoVeiculo.getText().isEmpty()) {
                    //retorno da camada de persistencia uma lista de objetos contendo o codigo do cliente igual ao informado na no campo codigo cliente
                    ArrayList<Veiculo> lista = this.persistencia.retornarListaVeiculos(0, txtCodigoVeiculo.getText());
                    // se a lista nao for vazia
                    if (lista.isEmpty()) {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoVeiculo.setText("");
                        this.repaint();
                        //instancio a tela de cadastro de clientes
                        this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
                        //centralizo a exibicao do componente no JFrame
                        this.telaConsultaVeiculo.setLocationRelativeTo(null);
                        //torno visivel a tela de cadastro de clientes
                        this.telaConsultaVeiculo.setVisible(true);
                    } else {
                        //limpo o campo de texto codigo cliente
                        this.txtCodigoVeiculo.setText("");
                        this.repaint();
                        //seto o objeto cliente selecionado
                        this.indicarVeiculo(lista.get(0));
                        //seto o foco para o campo codigo cliente
                        this.txtPlaca.requestFocus();
                    }
                } else {
                    //instancio a tela de cadastro de clientes
                    this.telaConsultaVeiculo = new TelaConsultaVeiculo(telaPrincipal, true, persistencia, this);
                    //centralizo a exibicao do componente no JFrame
                    this.telaConsultaVeiculo.setLocationRelativeTo(null);
                    //torno visivel a tela de cadastro de clientes
                    this.telaConsultaVeiculo.setVisible(true);
                    this.txtCodigoVeiculo.setText("");
                    this.repaint();
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, "private void txtCodigoVeiculoKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCodigoVeiculoKeyPressed

    private void txtCorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorKeyTyped
        try {
            String numeros = "0123456789/;.,-";
            // se a tecla pressionada estiver contido no numeros
            if (numeros.contains(String.valueOf(evt.getKeyChar()))) {
                //elimino o evento gerado
                evt.consume();
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void txtCorKeyTyped()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtCorKeyTyped

    private void txtPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!txtPlaca.getText().isEmpty()) {
                    Reserva r = this.persistencia.retornarPlacaCadastrada(txtPlaca.getText(), reserva.getCodigo());
                    if (r == null) {
                        this.txtCor.requestFocus();
                    } else {
                        int resposta = JOptionPane.showConfirmDialog(this, "Placa já vinculada à outra reserva com situação aberta. Deseja visulizar ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            this.reserva = r;
                            indicarReserva(reserva);
                        } else if (resposta == JOptionPane.NO_OPTION) {
                            this.txtPlaca.requestFocus();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Informe a placa do veículo !");
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                if (!txtPlaca.getText().isEmpty()) {
                    Reserva r = this.persistencia.retornarPlacaCadastrada(txtPlaca.getText(), reserva.getCodigo());
                    if (r == null) {
                        this.txtCor.requestFocus();
                    } else {
                        int resposta = JOptionPane.showConfirmDialog(this, "Placa já vinculada à outra reserva com situação aberta. Deseja visulizar ?", "CONFIRMAÇÃO", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            this.reserva = r;
                            indicarReserva(reserva);
                        } else if (resposta == JOptionPane.NO_OPTION) {
                            this.txtPlaca.requestFocus();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Informe a placa do veículo !");
                }
            }
        } catch (Exception ex) {
            this.logger.imprimirLogger(ex.getLocalizedMessage(), TelaReserva.class, " private void txtPlacaKeyPressed()");
            JOptionPane.showMessageDialog(this, "Ocorreu uma exceção. Verifique o arquivo de log.");
        }
    }//GEN-LAST:event_txtPlacaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBarSuperior2;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCadastrarCliente;
    private javax.swing.JButton btnCadastrarVaga;
    private javax.swing.JButton btnCadastrarVeiculo;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCodigoCliente;
    private javax.swing.JLabel lblCodigoVaga;
    private javax.swing.JLabel lblCodigoVeiculo;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblCpfCnpj;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblDataSaida;
    private javax.swing.JLabel lblHoraEntrada;
    private javax.swing.JLabel lblHoraSaida;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNumeroVaga;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTotalHoras;
    private javax.swing.JLabel lblTotalReserva;
    private javax.swing.JLabel lblVagasOcupadas;
    private javax.swing.JLabel lblVagasTotais;
    private javax.swing.JLabel lblValorHora;
    private javax.swing.JPanel painelDadosReserva;
    private javax.swing.JPanel painelFundoReserva;
    private javax.swing.JPanel painelFundoVagas;
    private javax.swing.JPanel painelvagas;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextArea txtAreaObservacao;
    private javax.swing.JFormattedTextField txtCPFCNPJ;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCodigoCliente;
    private javax.swing.JFormattedTextField txtCodigoVaga;
    private javax.swing.JFormattedTextField txtCodigoVeiculo;
    private javax.swing.JFormattedTextField txtCor;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    private javax.swing.JFormattedTextField txtDescricaoVeiculo;
    private javax.swing.JFormattedTextField txtHoraEntrada;
    private javax.swing.JFormattedTextField txtHoraSaida;
    private javax.swing.JFormattedTextField txtMarca;
    private javax.swing.JFormattedTextField txtModelo;
    private javax.swing.JFormattedTextField txtNomeCliente;
    private javax.swing.JFormattedTextField txtNumeroVaga;
    private javax.swing.JFormattedTextField txtPlaca;
    private javax.swing.JFormattedTextField txtReferenciaVaga;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JFormattedTextField txtTotalHoras;
    private javax.swing.JFormattedTextField txtValorHora;
    // End of variables declaration//GEN-END:variables

    /**
     * Classe interna que tem a responsabilidade de tratar os eventos gerados
     * pela ação de um botao
     */
    private class ClickBotao implements ActionListener {

        //Objeto responsavel por receber o botao clicado pelo usuario
        private JButton botaoClicado;
        //Atributo responsavel por armazenar o numero da vaga
        private int numeroVaga;
        //Objeto responsavel por realizar a impressao de eventuais erros gerados pela aplicação no arquivo de log
        private PrintLogger logger = new PrintLogger();

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //se o painel de vagas estiver habilitado
                if (painelvagas.isEnabled()) {
                    //habilito o formulario de dados da reserva
                    habilitarFormulario(true);
                    //converto o objeto recebido em um action perform em um JButton
                    botaoClicado = (JButton) e.getSource();
                    // converto o texto informado no botao selecionado em um inteiro
                    numeroVaga = Integer.parseInt(botaoClicado.getText());
                    // se a cor de fundo do botao selecionado for igual ao vermelho
                    if (botaoClicado.getBackground() == Color.RED) {
                        // se o retorno nao for nulo
                        if (persistencia.retornarReservaSelecionada(numeroVaga) != null) {
                            limparCampos();
                            // seto o objeto selecionado para o formulario
                            indicarReserva(persistencia.retornarReservaSelecionada(numeroVaga));
                            //seto as datas de saida para o formulario
                            carregarDataSaida();
                            //chamada ao metodo responsavel por calcular o total de horas de uma locação
                            calcularTotalHoras(txtDataEntrada.getText() + " " + txtHoraEntrada.getText(), txtDataSaida.getText() + " " + txtHoraSaida.getText());
                            //seto o calculo das horas para o campo de texto
                            txtTotalHoras.setText(conversorData.getTotalHrs());
                            //chamada ao metodo responsavel por calcular o total da reserva
                            calcularTotalReserva();
                            // seto o texto para o label
                            lblSituacao.setText("RESERVA : RESERVADA");
                            lblSituacao.setFont(new Font("tahoma", Font.BOLD, 12));
                            lblSituacao.setForeground(Color.blue);
                            //habilito o botao finalizar
                            btnFinalizar.setEnabled(true);
                            btnImprimir.setEnabled(true);
                            //torno editavel os campos do formulario
                            editavel(true);
                            botoesEditaveis(true);
                        }
                    } else {
                        //limpo os campos do formulario
                        limparCampos();
                        //chamada ao metodo responsavel por setar os dados na vaga no formulario
                        selecionarVagaCadastrada(numeroVaga);
                        //seto as datas de entrada para o formulario
                        carregarDataEntrada();
                        carregarDataSaida();
                        // seto o texto para o label
                        lblSituacao.setText("RESERVA : ABERTO");
                        lblSituacao.setFont(new Font("tahoma", Font.BOLD, 12));
                        lblSituacao.setForeground(Color.GREEN);
                        //habilito o botao finalizar
                        btnFinalizar.setEnabled(false);
                        btnImprimir.setEnabled(false);
                        //torno editavel os campos do formulario
                        editavel(true);
                        botoesEditaveis(true);
                    }
                }
            } catch (Exception ex) {
                this.logger.imprimirLogger(ex.getLocalizedMessage(), ClickBotao.class, "public void actionPerformed()");
                JOptionPane.showMessageDialog(painelFundoReserva, "Ocorreu uma exceção. Verifique o arquivo de log.");
            }
        }
    }
}
