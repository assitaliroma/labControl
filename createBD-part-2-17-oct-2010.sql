--
-- PostgreSQL database dump
--

-- Started on 2010-10-17 21:36:10 VET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 323 (class 2612 OID 24655)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: labcontrol_user
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO labcontrol_user;

SET search_path = public, pg_catalog;

--
-- TOC entry 19 (class 1255 OID 24656)
-- Dependencies: 323 6
-- Name: valid_phone(character varying); Type: FUNCTION; Schema: public; Owner: labcontrol_user
--

CREATE FUNCTION valid_phone(tel_var character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
BEGIN
	RETURN tel_var SIMILAR TO '[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]';
END;
$$;


ALTER FUNCTION public.valid_phone(tel_var character varying) OWNER TO labcontrol_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1510 (class 1259 OID 24657)
-- Dependencies: 6
-- Name: coordinaciones; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE coordinaciones (
    id character varying(16) NOT NULL,
    carrera character varying(32) NOT NULL,
    telefono character varying(11),
    coordinador character varying(60),
    turno character varying(16) NOT NULL
);


ALTER TABLE public.coordinaciones OWNER TO labcontrol_user;

--
-- TOC entry 1828 (class 0 OID 0)
-- Dependencies: 1510
-- Name: TABLE coordinaciones; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE coordinaciones IS 'Representacion de las coordinaciones de LabControl';


--
-- TOC entry 1511 (class 1259 OID 24660)
-- Dependencies: 6
-- Name: laboratorio_seq; Type: SEQUENCE; Schema: public; Owner: labcontrol_user
--

CREATE SEQUENCE laboratorio_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.laboratorio_seq OWNER TO labcontrol_user;

--
-- TOC entry 1829 (class 0 OID 0)
-- Dependencies: 1511
-- Name: SEQUENCE laboratorio_seq; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON SEQUENCE laboratorio_seq IS 'Contador para los ID de la tabla laboratorios';


--
-- TOC entry 1512 (class 1259 OID 24662)
-- Dependencies: 6
-- Name: laboratorios; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE laboratorios (
    id integer NOT NULL,
    nombre character varying(32) NOT NULL,
    capacidad integer NOT NULL
);


ALTER TABLE public.laboratorios OWNER TO labcontrol_user;

--
-- TOC entry 1830 (class 0 OID 0)
-- Dependencies: 1512
-- Name: TABLE laboratorios; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE laboratorios IS 'Representacion de laboratorios en LabControl';


--
-- TOC entry 1513 (class 1259 OID 24665)
-- Dependencies: 6
-- Name: materias; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE materias (
    id character varying(16) NOT NULL,
    nombre character varying(32) NOT NULL,
    coordinacion_id character varying(16) NOT NULL
);


ALTER TABLE public.materias OWNER TO labcontrol_user;

--
-- TOC entry 1831 (class 0 OID 0)
-- Dependencies: 1513
-- Name: TABLE materias; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE materias IS 'Representacion de materias en LabControl';


--
-- TOC entry 1520 (class 1259 OID 24731)
-- Dependencies: 6
-- Name: periodos; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE periodos (
    periodo_key character varying(16) NOT NULL,
    periodo_description character varying(64)
);


ALTER TABLE public.periodos OWNER TO labcontrol_user;

--
-- TOC entry 1514 (class 1259 OID 24668)
-- Dependencies: 6
-- Name: profesores; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE profesores (
    id integer NOT NULL,
    nombre character varying(60) NOT NULL,
    periodo character varying(16),
    telefono character varying(11),
    turno character varying(16),
    coordinacion_id character varying(16) NOT NULL
);


ALTER TABLE public.profesores OWNER TO labcontrol_user;

--
-- TOC entry 1832 (class 0 OID 0)
-- Dependencies: 1514
-- Name: TABLE profesores; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE profesores IS 'Representacion de profesores en LabControl';


--
-- TOC entry 1515 (class 1259 OID 24671)
-- Dependencies: 6
-- Name: profesores_seq; Type: SEQUENCE; Schema: public; Owner: labcontrol_user
--

CREATE SEQUENCE profesores_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.profesores_seq OWNER TO labcontrol_user;

--
-- TOC entry 1833 (class 0 OID 0)
-- Dependencies: 1515
-- Name: SEQUENCE profesores_seq; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON SEQUENCE profesores_seq IS 'Contador para los ID de la tabla profesores de LabControl';


--
-- TOC entry 1516 (class 1259 OID 24673)
-- Dependencies: 6
-- Name: roles; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE roles (
    rol character varying(16) NOT NULL
);


ALTER TABLE public.roles OWNER TO labcontrol_user;

--
-- TOC entry 1834 (class 0 OID 0)
-- Dependencies: 1516
-- Name: TABLE roles; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE roles IS '[AUTH] Para el control de Roles en la aplicacion LabControl';


--
-- TOC entry 1517 (class 1259 OID 24676)
-- Dependencies: 6
-- Name: turnos; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE turnos (
    turno_key character varying(16) NOT NULL,
    turno_description character varying(64)
);


ALTER TABLE public.turnos OWNER TO labcontrol_user;

--
-- TOC entry 1836 (class 0 OID 0)
-- Dependencies: 1517
-- Name: TABLE turnos; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE turnos IS 'Turnos validos en LabControl';


--
-- TOC entry 1518 (class 1259 OID 24679)
-- Dependencies: 6
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE usuario_rol (
    usuario character varying(16) NOT NULL,
    rol character varying(16) NOT NULL
);


ALTER TABLE public.usuario_rol OWNER TO labcontrol_user;

--
-- TOC entry 1837 (class 0 OID 0)
-- Dependencies: 1518
-- Name: TABLE usuario_rol; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE usuario_rol IS '[AUTH] Define los roles que puede tener un usuario en LabControl';


--
-- TOC entry 1519 (class 1259 OID 24682)
-- Dependencies: 6
-- Name: usuarios; Type: TABLE; Schema: public; Owner: labcontrol_user; Tablespace: 
--

CREATE TABLE usuarios (
    usuario character varying(16) NOT NULL,
    credencial character varying(16) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO labcontrol_user;

--
-- TOC entry 1839 (class 0 OID 0)
-- Dependencies: 1519
-- Name: TABLE usuarios; Type: COMMENT; Schema: public; Owner: labcontrol_user
--

COMMENT ON TABLE usuarios IS '[AUTH] Define los usuarios de LabControl';


--
-- TOC entry 1799 (class 2606 OID 24686)
-- Dependencies: 1510 1510
-- Name: coordinacion_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY coordinaciones
    ADD CONSTRAINT coordinacion_pk PRIMARY KEY (id);


--
-- TOC entry 1801 (class 2606 OID 24688)
-- Dependencies: 1512 1512
-- Name: laboratorios_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY laboratorios
    ADD CONSTRAINT laboratorios_pk PRIMARY KEY (id);


--
-- TOC entry 1803 (class 2606 OID 24690)
-- Dependencies: 1513 1513
-- Name: materias_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY materias
    ADD CONSTRAINT materias_pk PRIMARY KEY (id);


--
-- TOC entry 1815 (class 2606 OID 24735)
-- Dependencies: 1520 1520
-- Name: periodos_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY periodos
    ADD CONSTRAINT periodos_pk PRIMARY KEY (periodo_key);


--
-- TOC entry 1805 (class 2606 OID 24692)
-- Dependencies: 1514 1514
-- Name: profesores_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY profesores
    ADD CONSTRAINT profesores_pk PRIMARY KEY (id);


--
-- TOC entry 1807 (class 2606 OID 24694)
-- Dependencies: 1516 1516
-- Name: roles_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pk PRIMARY KEY (rol);


--
-- TOC entry 1809 (class 2606 OID 24696)
-- Dependencies: 1517 1517
-- Name: turnos_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY turnos
    ADD CONSTRAINT turnos_pk PRIMARY KEY (turno_key);


--
-- TOC entry 1811 (class 2606 OID 24698)
-- Dependencies: 1518 1518 1518
-- Name: user_role_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT user_role_pk PRIMARY KEY (usuario, rol);


--
-- TOC entry 1813 (class 2606 OID 24700)
-- Dependencies: 1519 1519
-- Name: users_pk; Type: CONSTRAINT; Schema: public; Owner: labcontrol_user; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT users_pk PRIMARY KEY (usuario);


--
-- TOC entry 1816 (class 2606 OID 24701)
-- Dependencies: 1808 1510 1517
-- Name: coordinaciones_turnos_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY coordinaciones
    ADD CONSTRAINT coordinaciones_turnos_fk FOREIGN KEY (turno) REFERENCES turnos(turno_key);


--
-- TOC entry 1817 (class 2606 OID 24706)
-- Dependencies: 1513 1798 1510
-- Name: materias_coordinaciones_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY materias
    ADD CONSTRAINT materias_coordinaciones_fk FOREIGN KEY (coordinacion_id) REFERENCES coordinaciones(id);


--
-- TOC entry 1818 (class 2606 OID 24711)
-- Dependencies: 1514 1798 1510
-- Name: profesores_coordinaciones_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY profesores
    ADD CONSTRAINT profesores_coordinaciones_fk FOREIGN KEY (coordinacion_id) REFERENCES coordinaciones(id);


--
-- TOC entry 1820 (class 2606 OID 24736)
-- Dependencies: 1514 1814 1520
-- Name: profesores_periodos_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY profesores
    ADD CONSTRAINT profesores_periodos_fk FOREIGN KEY (periodo) REFERENCES periodos(periodo_key);


--
-- TOC entry 1819 (class 2606 OID 24716)
-- Dependencies: 1808 1514 1517
-- Name: profesores_turnos_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY profesores
    ADD CONSTRAINT profesores_turnos_fk FOREIGN KEY (turno) REFERENCES turnos(turno_key);


--
-- TOC entry 1821 (class 2606 OID 24721)
-- Dependencies: 1806 1518 1516
-- Name: role_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT role_fk FOREIGN KEY (rol) REFERENCES roles(rol);


--
-- TOC entry 1822 (class 2606 OID 24726)
-- Dependencies: 1519 1812 1518
-- Name: user_fk; Type: FK CONSTRAINT; Schema: public; Owner: labcontrol_user
--

ALTER TABLE ONLY usuario_rol
    ADD CONSTRAINT user_fk FOREIGN KEY (usuario) REFERENCES usuarios(usuario);


--
-- TOC entry 1827 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 1835 (class 0 OID 0)
-- Dependencies: 1516
-- Name: roles; Type: ACL; Schema: public; Owner: labcontrol_user
--

REVOKE ALL ON TABLE roles FROM PUBLIC;
REVOKE ALL ON TABLE roles FROM labcontrol_user;
GRANT ALL ON TABLE roles TO labcontrol_user;
GRANT SELECT ON TABLE roles TO security_user;


--
-- TOC entry 1838 (class 0 OID 0)
-- Dependencies: 1518
-- Name: usuario_rol; Type: ACL; Schema: public; Owner: labcontrol_user
--

REVOKE ALL ON TABLE usuario_rol FROM PUBLIC;
REVOKE ALL ON TABLE usuario_rol FROM labcontrol_user;
GRANT ALL ON TABLE usuario_rol TO labcontrol_user;
GRANT SELECT ON TABLE usuario_rol TO security_user;


--
-- TOC entry 1840 (class 0 OID 0)
-- Dependencies: 1519
-- Name: usuarios; Type: ACL; Schema: public; Owner: labcontrol_user
--

REVOKE ALL ON TABLE usuarios FROM PUBLIC;
REVOKE ALL ON TABLE usuarios FROM labcontrol_user;
GRANT ALL ON TABLE usuarios TO labcontrol_user;
GRANT SELECT ON TABLE usuarios TO security_user;


-- Completed on 2010-10-17 21:36:10 VET

--
-- PostgreSQL database dump complete
--

