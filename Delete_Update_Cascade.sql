-- Foreign Key: profesores_coordinaciones_fk

-- ALTER TABLE profesores DROP CONSTRAINT profesores_coordinaciones_fk;

ALTER TABLE profesores
  ADD CONSTRAINT profesores_coordinaciones_fk FOREIGN KEY (coordinacion_id)
      REFERENCES coordinaciones (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE profesores
  ADD CONSTRAINT profesores_periodos_fk FOREIGN KEY (periodo)
      REFERENCES periodos (periodo_key) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE profesores
  ADD CONSTRAINT profesores_turnos_fk FOREIGN KEY (turno)
      REFERENCES turnos (turno_key) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

      ALTER TABLE materias
  ADD CONSTRAINT materias_coordinaciones_fk FOREIGN KEY (coordinacion_id)
      REFERENCES coordinaciones (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

      ALTER TABLE coordinaciones
  ADD CONSTRAINT coordinaciones_turnos_fk FOREIGN KEY (turno)
      REFERENCES turnos (turno_key) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;

      ALTER TABLE usuario_rol
  ADD CONSTRAINT role_fk FOREIGN KEY (rol)
      REFERENCES roles (rol) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE usuario_rol
  ADD CONSTRAINT user_fk FOREIGN KEY (usuario)
      REFERENCES usuarios (usuario) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
