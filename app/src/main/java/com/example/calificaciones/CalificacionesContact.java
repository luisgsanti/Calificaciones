package com.example.calificaciones;

import android.provider.BaseColumns;

public final class CalificacionesContact {

    private CalificacionesContact(){

    }

    public static final class MateriaEntry implements BaseColumns{

        public final static String TABLE_NAME = "materias";
        public final static String _ID = BaseColumns._ID;
        public final static String CODIGO_MATERIA = "codigoMateria";
        public final static String NOMBRE_MATERIA = "nombreMateria";
        public final static String DEFINITIVA_MATERIA = "definitivaMateria";

    }

    public static final class ActividadEntry implements BaseColumns{

        public final static String TABLE_NAME = "actividades";
        public final static String _ID = BaseColumns._ID;
        public final static String NOMBRE_ACTIVIDAD = "nombreActividad";
        public final static String PORCENTAJE_ACTIVIDAD = "porcentajeActividad";
        public final static String NOTA = "notaActividad";
        public final static String CORTE = "corte";
        public final static String CODIGO_MATERIA = "codigoMateria";

    }
}

