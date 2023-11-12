package com.miempresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TareaController {

    @GetMapping("/verNumerosPrimos")
    public String verNumerosPrimos(
            @RequestParam(name = "inicio") int inicio,
            @RequestParam(name = "fin") int fin,
            Model model) {

        List<Integer> numerosPrimos = calcularNumerosPrimosEnRango(inicio, fin);

        model.addAttribute("numerosPrimos", numerosPrimos);

        return "resultados";
    }

    @GetMapping("/verTablaMultiplicar")
    public String verTablaMultiplicar(
            @RequestParam(name = "numeroTabla") int numeroTabla,
            Model model) {

        List<ResultadoMultiplicacion> resultadosMultiplicacion = generarTablaMultiplicar(numeroTabla);

        model.addAttribute("tablaMultiplicar", resultadosMultiplicacion);

        return "resultados2";
    }

    private List<Integer> calcularNumerosPrimosEnRango(int inicio, int fin) {
        List<Integer> numerosPrimos = new ArrayList<>();

        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                numerosPrimos.add(i);
            }
        }

        return numerosPrimos;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    private List<ResultadoMultiplicacion> generarTablaMultiplicar(int numeroTabla) {
        List<ResultadoMultiplicacion> resultadosMultiplicacion = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int resultado = numeroTabla * i;
            String operacion = numeroTabla + " x " + i;
            boolean esPar = resultado % 2 == 0;
            resultadosMultiplicacion.add(new ResultadoMultiplicacion(operacion, resultado, esPar));
        }

        return resultadosMultiplicacion;
    }

    private static class ResultadoMultiplicacion {
        private String operacion;
        private int resultado;
        private boolean esPar;

        public ResultadoMultiplicacion(String operacion, int resultado, boolean esPar) {
            this.operacion = operacion;
            this.resultado = resultado;
            this.esPar = esPar;
        }

        public String getOperacion() {
            return operacion;
        }

        public int getResultado() {
            return resultado;
        }

        public boolean isEsPar() {
            return esPar;
        }
    }
}

