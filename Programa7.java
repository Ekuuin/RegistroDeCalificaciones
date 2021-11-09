
/*
Programa para el manejo de un Sistema de Registro de Calificaciones
*/
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.text.Collator;

class UnidadDeAprendizaje { // Atributos
    private byte Id;
    private String Nombre;
    private String Area;
    private String Tipo;
    private byte NumCreditos;
    private byte NumHorasSemana;

    // Métodos
    UnidadDeAprendizaje() {
        Id = 0;
        Nombre = null;
        Area = null;
        Tipo = null;
        NumCreditos = 0;
        NumHorasSemana = 0;
    }

    public void SetId(byte n) {
        Id = n;
    }

    public void SetNombre(String aux) {
        Nombre = aux;
    }

    public void SetArea(String aux) {
        Area = aux;
    }

    public void SetTipo(String aux) {
        Tipo = aux;
    }

    public void SetNumeroCreditos(byte n) {
        NumCreditos = n;
    }

    public void SetNumeroHorasSemana(byte n) {
        NumHorasSemana = n;
    }

    public String GetArea() {
        return Area;
    }

    public String GetTipo() {
        return Tipo;
    }

    public byte GetCreditos() {
        return NumCreditos;
    }

    public void PrintUDA() {
        System.out.println("Id: " + Id);
        System.out.println("Nombre de la UDA: " + Nombre);
        System.out.println("Area: " + Area);
        System.out.println("Tipo: " + Tipo);
        System.out.println("Numero de Creditos: " + NumCreditos);
        System.out.println("Numero de Horas/Semana: " + NumHorasSemana);
    }
}// FIN CLASE UNIDAD DE APRENDIZAJE

class ListadoUDAS {
    public UnidadDeAprendizaje[] UDA;

    // Método Constructor por Default
    ListadoUDAS() {
        byte k;
        UDA = new UnidadDeAprendizaje[72];
        for (k = 0; k < 72; k++)
            UDA[k] = new UnidadDeAprendizaje();
    }

    public void LecturaArchivoUDAS() {
        byte k;
        File Archivo;

        // Abrir Archivo
        Archivo = new File("ListadoUDA.txt");
        try (Scanner Scanf = new Scanner(Archivo)) { // Asigna el Archivo1 como fuente de entrada para el Objeto Scanner
            for (k = 0; k < 72; k++) {
                UDA[k].SetId(Scanf.nextByte());
                Scanf.nextLine();
                UDA[k].SetNombre(Scanf.nextLine());
                UDA[k].SetArea(Scanf.nextLine());
                UDA[k].SetTipo(Scanf.nextLine());
                UDA[k].SetNumeroCreditos(Scanf.nextByte());
                UDA[k].SetNumeroHorasSemana(Scanf.nextByte());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void PrintListaUDAS() {
        byte k;
        for (k = 0; k < 72; k++)
            UDA[k].PrintUDA();
    }

}// FIN CLASE LISTADO UDAS

class StatusUDA {
    private float Calificacion;
    private byte Oportunidad;
    private byte Semestre;
    private boolean Aprobada;

    StatusUDA() {
        Calificacion = -1.0f;
        Oportunidad = 0;
        Semestre = 0;
        Aprobada = false;
    }

    public float GetCalificacion() {
        return Calificacion;
    }

    public byte GetOportunidad() {
        return Oportunidad;
    }

    public byte GetSemestre() {
        return Semestre;
    }

    public boolean GetAprobada() {
        return Aprobada;
    }

    public void SetCalificacion(float c) {
        Calificacion = c;
        if (!(Calificacion < 7.0f))
            Aprobada = true;
    }

    public void SetOportunidad(byte op) {
        Oportunidad = op;
    }

    public void SetSemestre(byte sem) {
        Semestre = sem;
    }

    public void PrintStatus() {
        System.out.println("Calificación: " + Calificacion);
        System.out.println("Oportunidad: " + Oportunidad);
        System.out.println("Semestre: " + Semestre);
        if (Aprobada)
            System.out.println("APROBADA");
        else
            System.out.println("NO Aprobada");
    }
}// FIN DE LA CLASE StatusUDA

class Persona {
    private String Nombres;
    private String Apaterno;
    private String Amaterno;

    Persona() {
        Nombres = null;
        Apaterno = null;
        Amaterno = null;
    }

    public String GetNombre() {
        return Nombres;
    }

    public String GetApaterno() {
        return Apaterno;
    }

    public String GetAmaterno() {
        return Amaterno;
    }

    public void SetNombre(String Label) {
        Nombres = Label;
    }

    public void SetApaterno(String Label) {
        Apaterno = Label;
    }

    public void SetAmaterno(String Label) {
        Amaterno = Label;
    }

    public void PrintNombre() {
        System.out.println("Profesor: " + Nombres + " " + Apaterno + " " + Amaterno);
    }

}// FIN DE LA CLASE Persona

class Registro {
    private byte Id;
    private byte Id_UDA;
    public StatusUDA Status;
    public Persona Prof;

    Registro() {
        Id_UDA = 0;
        Status = new StatusUDA();
        Prof = new Persona();
    }

    public void SetIdRegistro(byte n) {
        Id = n;
    }

    public void SetIdUDA(byte n) {
        Id_UDA = n;
    }

    public byte GetId() {
        return Id;
    }

    public byte GetIdUDA() {
        return Id_UDA;
    }

    public StatusUDA GetStatusUDA() {
        return Status;
    }

    public Persona GetProf() {
        return Prof;
    }

    public void PrintRegistro(ListadoUDAS Listado) {
        Listado.UDA[Id_UDA - 1].PrintUDA();
        Status.PrintStatus();
        Prof.PrintNombre();
        System.out.println("");
    }
}

class Nodo {
    public Registro reg;
    private Nodo next;

    public Nodo(Registro aux, Nodo sig) {
        reg = aux;
        next = sig;
    }

    public void SetRegistro(Registro aux) {
        reg = aux;
    }

    public void SetNext(Nodo aux) {
        next = aux;
    }

    public void SetNull() {
        next = null;
    }

    public Registro GetRegistro() {
        return reg;
    }

    public Nodo GetNext() {
        return next;
    }
}

class Lista {
    private Nodo Inicio;
    private int NumElem;

    public Lista() {
        Inicio = null;
        NumElem = 0;
    }

    public void InsertarNodo(Registro data) {
        Nodo NewNodo = new Nodo(data, null);
        Nodo auxNodo;
        if (Inicio == null) { // Lista enlazada vacia
            Inicio = NewNodo;
            NumElem++;
        } else { // Lista contiene al menos un elemento
            auxNodo = Inicio;
            while (auxNodo.GetNext() != null)
                auxNodo = auxNodo.GetNext();
            auxNodo.SetNext(NewNodo);
            NumElem++;
        }
    }

    public void EliminarNodo() {
        Nodo auxNodo, anteriorNodo;
        if (Inicio == null)
            System.out.println("No hay registros.");
        else {
            if (Inicio.GetNext() == null) {
                Inicio = null;
            } else {
                auxNodo = Inicio;
                while (auxNodo.GetNext() != null) {
                    anteriorNodo = auxNodo;
                    auxNodo = auxNodo.GetNext();
                    if (auxNodo.GetNext() == null) {
                        anteriorNodo.SetNull();
                        System.out.println("Registro eliminado");
                    }
                }
                NumElem--;
            }
        }
    }

    public void PrintLista(ListadoUDAS Listado) {
        Nodo auxNodo;
        if (Inicio == null) { // Lista enlazada vacia
            System.out.println("Lista esta vacia!");
        } else { // Lista contiene al menos un elemento
            auxNodo = Inicio;
            do {
                auxNodo.reg.PrintRegistro(Listado);
                auxNodo = auxNodo.GetNext();
            } while (auxNodo != null);
        }
    }

    public Nodo GetInicio() {
        return Inicio;
    }

    public int GetNumeroElementos() {
        return NumElem;
    }

}

class Programa7 {
    public static void main(String arg[]) {
        ListadoUDAS Listado = new ListadoUDAS();
        Scanner Scanf = new Scanner(System.in);
        byte opcion;
        boolean salir = false;
        byte ContadorRegistros = 1;

        Lista ListaDeRegistros = new Lista();
        Listado.LecturaArchivoUDAS();

        do {
            System.out.println("- Sistema de Registro de Calificaciones - ");
            System.out.println("1. Ingresar Registro");
            System.out.println("2. Listar Registros");
            System.out.println("3. Eliminar último registro");
            System.out.println("4. Listar UDAs por semestre");
            System.out.println("5. Listar UDAs por área");
            System.out.println("6. Listar UDAs optativas");
            System.out.println("7. Buscar Registro");
            System.out.println("8. Promedio por semestre");
            System.out.println("9. Promedio general");
            System.out.println("10. Número de creditos cursados");
            System.out.println("11. Número de creditos cursados Aprobados");
            System.out.println("12. Número de creditos cursados No Aprobados");
            System.out.println("13. Cargar Archivo de Registros");
            System.out.println("14. Guardar Archivo de Registros");
            System.out.println("15. Salir");
            System.out.print("Indique su elección: ");
            opcion = Scanf.nextByte();
            System.out.println("");
            switch (opcion) {
            case 1:
                ListaDeRegistros.InsertarNodo(NuevoRegistro(ContadorRegistros));
                ContadorRegistros++;
                break;
            case 2:
                ListaDeRegistros.PrintLista(Listado);
                break;
            case 3:
                ListaDeRegistros.EliminarNodo();
                if (ContadorRegistros > 1)
                    ContadorRegistros--;
                break;
            case 4:
                UDASxSemestre(ListaDeRegistros, Listado);
                break;
            case 5:
                UDASxArea(ListaDeRegistros, Listado);
                break;
            case 6:
                UDASxOptativa(ListaDeRegistros, Listado);
                break;
            case 7:
                BuscarRegistro(ListaDeRegistros, Listado);
                break;
            case 8:
                printPromedioSemestral(ListaDeRegistros);
                break;
            case 9:
                printPromedioGeneral(ListaDeRegistros);
                break;
            case 10:
                NumCreditosCursados(ListaDeRegistros, Listado);
                break;
            case 11:
                NumCreditosCursadosAprobados(ListaDeRegistros, Listado);
                break;
            case 12:
                NumCreditosCursadosNoAprobados(ListaDeRegistros, Listado);
                break;
            case 13:
                ListaDeRegistros = LeerArchivoRegistros();
                break;
            case 14:
                GuardarRegistros(ListaDeRegistros);
                break;
            case 15:
                salir = true;
                break;
            default:
                System.out.println("Opcion no valida. Vuelva a intentarlo...");
            } // FIN DEL SWITCH
        } while (!salir);
    }// FIN DEL METODO PRINCIPAL

    public static Lista LeerArchivoRegistros() {
        File Archivo;
        int Total, k;
        Lista ListReg = new Lista();

        // Abrir Archivo mediante la generación de una instancia de la clase File
        Archivo = new File("Registro.txt");
        try { // Asigna el Archivo1 como fuente de entrada para el Objeto Scanner
            Scanner Scanf = new Scanner(Archivo);

            // Leer una linea de texto desde el archivo
            Total = Scanf.nextInt();
            for (k = 0; k < Total; k++) {
                Registro NewRegistro = new Registro();

                NewRegistro.SetIdRegistro(Scanf.nextByte());
                NewRegistro.SetIdUDA(Scanf.nextByte());
                NewRegistro.Status.SetCalificacion(Scanf.nextFloat());
                NewRegistro.Status.SetOportunidad(Scanf.nextByte());
                NewRegistro.Status.SetSemestre(Scanf.nextByte());
                Scanf.nextLine();
                NewRegistro.Prof.SetNombre(Scanf.nextLine());
                NewRegistro.Prof.SetApaterno(Scanf.nextLine());
                NewRegistro.Prof.SetAmaterno(Scanf.nextLine());
                ListReg.InsertarNodo(NewRegistro);
            }
            // Cerrar el Archivo
            Scanf.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ListReg;
    } // FIN DE LECTURA DE ARCHIVO

    public static Registro NuevoRegistro(byte N) {
        Scanner Scanf = new Scanner(System.in);
        Registro NewRegistro = new Registro();
        NewRegistro.SetIdRegistro(N);
        System.out.println("Numero de Id de la UDA a registrar: ");
        NewRegistro.SetIdUDA(Scanf.nextByte());
        System.out.println("Calificación: ");
        NewRegistro.Status.SetCalificacion(Scanf.nextFloat());
        System.out.println("Oportunidad: ");
        NewRegistro.Status.SetOportunidad(Scanf.nextByte());
        System.out.println("Semestre en que la curso: ");
        NewRegistro.Status.SetSemestre(Scanf.nextByte());
        Scanf.nextLine();
        System.out.println("Nombre(s) del profesor: ");
        NewRegistro.Prof.SetNombre(Scanf.nextLine());
        System.out.println("Apellido paterno: ");
        NewRegistro.Prof.SetApaterno(Scanf.nextLine());
        System.out.println("Apellido materno: ");
        NewRegistro.Prof.SetAmaterno(Scanf.nextLine());
        return NewRegistro;
    }

    public static void GuardarRegistros(Lista ListReg) {
        int k;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        Registro auxReg;
        StatusUDA auxStatus;
        Persona auxPer;

        try {
            if (auxNodo != null) // Verificar si la lista no esta vacia
            {
                FileWriter Origen = new FileWriter("Registro.txt", false);
                PrintWriter Archivo = new PrintWriter(Origen);
                Archivo.println(Total);
                for (k = 0; k < Total; k++) {
                    auxReg = auxNodo.GetRegistro();
                    Archivo.println(auxReg.GetId());
                    Archivo.println(auxReg.GetIdUDA());
                    auxStatus = auxReg.GetStatusUDA();
                    Archivo.println(auxStatus.GetCalificacion());
                    Archivo.println(auxStatus.GetOportunidad());
                    Archivo.println(auxStatus.GetSemestre());
                    auxPer = auxReg.GetProf();
                    Archivo.println(auxPer.GetNombre());
                    Archivo.println(auxPer.GetApaterno());
                    Archivo.println(auxPer.GetAmaterno());
                    auxNodo = auxNodo.GetNext();
                }
                System.out.println("Se escribio el archivo ");
                Archivo.close();
            } else
                System.out.println("No existen registros a guardar.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void BuscarRegistro(Lista ListReg, ListadoUDAS Listado) {
        boolean msg = true;
        byte ID;
        int k;
        Scanner Scanf = new Scanner(System.in);
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();

        if (auxNodo != null) {
            System.out.println("Ingrese el numero de registro que desea ver:");
            ID = Scanf.nextByte();
            for (k = 0; k < Total; k++) {
                if (auxNodo.reg.GetId() == ID) {
                    auxNodo.reg.PrintRegistro(Listado);
                    msg = false;
                    break;
                }
                auxNodo = auxNodo.GetNext();
            }
            if (msg)
                System.out.println("No se encontró el registro.");
        } else
            System.out.println("No hay registros.");
    }

    public static void printPromedioGeneral(Lista ListReg) {
        int k;
        float prom = 0, j = 0;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        StatusUDA auxStatus;

        if (auxNodo != null) {
            for (k = 0; k < Total; k++) {
                auxStatus = auxNodo.reg.GetStatusUDA();
                prom += auxStatus.GetCalificacion();
                j++;
                auxNodo = auxNodo.GetNext();
            }
            prom = prom / j;
            System.out.printf("El promedio general es: %.2f\n", prom);
        } else
            System.out.println("No hay registros.");
    }

    public static void printPromedioSemestral(Lista ListReg) {
        byte ID;
        int k;
        float prom = 0, j = 0;
        Scanner Scanf = new Scanner(System.in);
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        StatusUDA auxStatus;

        if (auxNodo != null) {
            System.out.println("Ingrese el semestre:");
            ID = Scanf.nextByte();
            for (k = 0; k < Total; k++) {
                auxStatus = auxNodo.reg.GetStatusUDA();
                if (auxStatus.GetSemestre() == ID) {
                    prom += auxStatus.GetCalificacion();
                    j++;
                }
                auxNodo = auxNodo.GetNext();
            }
            prom = prom / j;
            System.out.printf("El promedio del semestre %x es: %.2f\n", ID, prom);
        } else
            System.out.println("No hay registros.");
    }

    public static void UDASxSemestre(Lista ListReg, ListadoUDAS Listado) {
        boolean msg = true;
        byte ID;
        int k;
        Scanner Scanf = new Scanner(System.in);
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        StatusUDA auxStatus;

        if (auxNodo != null) {
            System.out.println("Ingrese el numero de semestre que desea ver sus materias:");
            ID = Scanf.nextByte();
            for (k = 0; k < Total; k++) {
                auxStatus = auxNodo.reg.GetStatusUDA();
                if (auxStatus.GetSemestre() == ID) {
                    auxNodo.reg.PrintRegistro(Listado);
                    msg = false;
                }
                auxNodo = auxNodo.GetNext();
            }
            if (msg)
                System.out.println("No hay materias en ese semestre.");
        } else
            System.out.println("No hay registros.");
    }

    public static void UDASxArea(Lista ListReg, ListadoUDAS Listado) {
        Collator c = Collator.getInstance();
        boolean msg = true;
        String Area;
        byte Id_UDA;
        int k;
        Scanner Scanf = new Scanner(System.in);
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();

        c.setStrength(Collator.PRIMARY);

        if (auxNodo != null) {
            System.out.println("Ingrese el área de las materias que desea obtener: ");
            Area = Scanf.nextLine();
            for (k = 0; k < Total; k++) {
                Id_UDA = auxNodo.reg.GetIdUDA();
                if (c.equals(Listado.UDA[Id_UDA - 1].GetArea(), Area)) {
                    auxNodo.reg.PrintRegistro(Listado);
                    msg = false;
                }
                auxNodo = auxNodo.GetNext();
            }
            if (msg)
                System.out.println("No hay materias de esa área.");
        } else
            System.out.println("No hay registros.");
    }

    public static void UDASxOptativa(Lista ListReg, ListadoUDAS Listado) {
        boolean msg = true;
        String[] partes;
        byte Id_UDA;
        int k;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();

        if (auxNodo != null) {
            for (k = 0; k < Total; k++) {
                Id_UDA = auxNodo.reg.GetIdUDA();
                partes = Listado.UDA[Id_UDA - 1].GetTipo().split(" ");
                if (partes[0].equals("Optativa")) {
                    auxNodo.reg.PrintRegistro(Listado);
                    msg = false;
                }
                auxNodo = auxNodo.GetNext();
            }
            if (msg)
                System.out.println("No hay materias optativas cursadas.");
        } else
            System.out.println("No hay registros.");
    }

    public static void NumCreditosCursados(Lista ListReg, ListadoUDAS Listado) {
        int k;
        byte Id_UDA;
        int sumCreditos = 0;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();

        if (auxNodo != null) {
            for (k = 0; k < Total; k++) {
                Id_UDA = auxNodo.reg.GetIdUDA();
                sumCreditos += Listado.UDA[Id_UDA - 1].GetCreditos();
                auxNodo = auxNodo.GetNext();
            }
            System.out.println("El número de creditos cursados es: " + sumCreditos + "\n");
        } else
            System.out.println("No hay registros.\n");
    }

    public static void NumCreditosCursadosAprobados(Lista ListReg, ListadoUDAS Listado) {
        int k;
        byte Id_UDA;
        int sumCreditos = 0;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        StatusUDA auxStatusUDA;

        if (auxNodo != null) {
            for (k = 0; k < Total; k++) {
                auxStatusUDA = auxNodo.reg.GetStatusUDA();
                if (auxStatusUDA.GetAprobada()) {
                    Id_UDA = auxNodo.reg.GetIdUDA();
                    sumCreditos += Listado.UDA[Id_UDA - 1].GetCreditos();
                }
                auxNodo = auxNodo.GetNext();
            }
            System.out.println("El número de creditos cursados aprobados es: " + sumCreditos + "\n");
        } else
            System.out.println("No hay registros.\n");
    }

    public static void NumCreditosCursadosNoAprobados(Lista ListReg, ListadoUDAS Listado) {
        int k;
        byte Id_UDA;
        int sumCreditos = 0;
        int Total = ListReg.GetNumeroElementos();
        Nodo auxNodo = ListReg.GetInicio();
        StatusUDA auxStatusUDA;

        if (auxNodo != null) {
            for (k = 0; k < Total; k++) {
                auxStatusUDA = auxNodo.reg.GetStatusUDA();
                if(!auxStatusUDA.GetAprobada()){
                Id_UDA = auxNodo.reg.GetIdUDA();
                sumCreditos += Listado.UDA[Id_UDA - 1].GetCreditos();
                }
                auxNodo = auxNodo.GetNext();
            }
            System.out.println("El número de creditos cursados no aprobados es: " + sumCreditos + "\n");
        } else
            System.out.println("No hay registros.\n");
    }
}
// FIN DE LA CLASE PRINCIPAL