/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesInmobiliaria;

import Edificio.Casa;
import Edificio.Departamento;
import Edificio.Modelo;
import Persona.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bllv1
 */
public class MostrarDatos extends Urbanizacion{

    public MostrarDatos(Usuario usuario, String nombreUrbanizacion, String direccion, Etapa etapa, Modelo modelo) {
        super(usuario, nombreUrbanizacion, direccion, etapa, modelo);
    }
    //metodo para la consulta de etapas
    public String mostrarDatosEtapa(){
        int valor = 0;
        System.out.println("--------------------------Datos de la Urbanizacion " + this.getNombreUrbanizacion()+ "----------------------------");
        System.out.println("Direccion: " + this.getDireccion() + " Codigo: " + this.getCodigo());

        
        List<Etapa> etapaunica = new ArrayList<>();
        for (Etapa e:this.getEtapas()){
            if(!etapaunica.isEmpty()) {
                for (Etapa etas : etapaunica) {
                    if (etas.getNombreEtapa().equals(e.getNombreEtapa())) {
                        valor += 1;
                    }
                }
                if (valor == 0) {
                    etapaunica.add(e);
                }
            }
            if (etapaunica.isEmpty()) {
                etapaunica.add(e);
            }
            valor = 0;
        }
        
        for (Etapa huu : etapaunica) {
            System.out.print(huu);
        }
        System.out.println("\n--------------------------------------------------------------------------------------");
        return null;
}
    //metodo que muestre los datos de los modelos
        public void mostrarDatosModelo(){
            boolean opcion = false;
            int valor = 0;
            
            System.out.println("--------------------------Datos de la Urbanizacion " + this.getNombreUrbanizacion()+ "----------------------------");
            System.out.println("Direccion: " + this.getDireccion() + " Codigo: " + this.getCodigo());

            List<Modelo> modelounico = new ArrayList<>();
            for (Modelo e: this.getModelos()){
                if(!modelounico.isEmpty()) {
                    for (Modelo etas : modelounico) {
                        if (etas.getNombre().equalsIgnoreCase(e.getNombre())) {
                            valor += 1;
                        }
                    }
                    if (valor == 0) {
                        modelounico.add(e);
                    }
                }
                if(modelounico.isEmpty()) {
                    modelounico.add(e);
                }
            valor = 0;
        }
        for (Modelo huu : modelounico) {
            System.out.print(huu);
        }
       System.out.println("\n--------------------------------------------------------------------------------------");        

        }
       

    //metodo que muestre los modelos disponibles
        public void mostrarModelosDisponibles(){
//            for(Modelo modelounic:this.getModelos()){
//                System.out.println(modelounic.getNombre());
//            }    
            int cantC = 0;
            int cantD = 0;
            for (Modelo m : this.getModelos()){
                if(m instanceof Casa){
                    cantC+=1;
                }
                if(m instanceof Departamento){
                    cantD+=1;
                }
            }            
            System.out.println("\"----------------------Modelos Disponibles--------------------");
            if (cantC !=0){
                System.out.println("Casas: " + cantC);
            }
            if(cantD != 0){
                System.out.println("Departamentos: " + cantD);
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
}
