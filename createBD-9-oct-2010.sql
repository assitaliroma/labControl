--
-- PostgreSQL database dump
--

-- Started on 2010-10-09 19:49:53 VET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

-- Role: "labcontrol_user"

-- DROP ROLE labcontrol_user;

CREATE ROLE labcontrol_user LOGIN
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;

-- Role: "security_user"

-- DROP ROLE security_user;

CREATE ROLE security_user LOGIN
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE CONNECTION LIMIT 5;

--
-- TOC entry 1819 (class 1262 OID 16386)
-- Name: labcontrol_db; Type: DATABASE; Schema: -; Owner: labcontrol_user
--

CREATE DATABASE labcontrol_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_VE.utf8' LC_CTYPE = 'es_VE.utf8';


ALTER DATABASE labcontrol_db OWNER TO labcontrol_user;


