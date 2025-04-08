package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.dispositivo.*;

public class RonQI2SilverTest {

    private RonQI2Silver ron;

    @BeforeEach
    void setUp(){
        ron = new RonQI2Silver();
    }

    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    @Test
    @DisplayName("Test inicializar devuelve false porque no se conecta el sensor de presion")
    public void testInicializar_NoConectaSensorPresion() {
        DispositivoSilver disp = mock(DispositivoSilver.class);

        ron.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(false);
        
        assertFalse(ron.inicializar());
    }

    @Test
    @DisplayName("Test inicializar devuelve false porque no se conecta el sensor de sonido")
    public void testInicializar_NoConectaSensorSonido() {
        DispositivoSilver disp = mock(DispositivoSilver.class);

        ron.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(false);
        
        
        assertFalse(ron.inicializar());
        verify(disp).configurarSensorPresion();
    }

    @Test
    @DisplayName("Test inicializar devuelve true porque se conectan ambos sensores")
    public void testInicializar_ConectaAmbosSensores() {
        DispositivoSilver disp = mock(DispositivoSilver.class);

        ron.anyadirDispositivo(disp);
        when(disp.conectarSensorPresion()).thenReturn(true);
        when(disp.configurarSensorPresion()).thenReturn(true);
        when(disp.conectarSensorSonido()).thenReturn(true);
        when(disp.configurarSensorSonido()).thenReturn(true);
        
        assertTrue(ron.inicializar());
        verify(disp).configurarSensorPresion();
        verify(disp).configurarSensorSonido();
    }
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    //Realizado en el test anterior


    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
