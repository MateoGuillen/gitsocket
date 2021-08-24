-- EN PGADMIN4 CREAR BASE DE DATOS CON NOMBRE bd_hospital
-- INGRESAR A QUERY TOOLS, COPIAR CODIGO Y EJECUTAR
-- VERIFICAR EL CONTENIDO DE LAS TABLAS en SCHEMA, PUBLIC



SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET SESSION AUTHORIZATION 'postgres';

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 3006
-- Name: DATABASE bd_hospital; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE bd_hospital IS 'Trabajo practico de sist. distribuidos';


--
-- TOC entry 216 (class 1255 OID 16571)
-- Name: registrar(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.registrar1() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
    insert into public.log(fecha,ip_origen,ip_destino,operacion,id_cama,id_hospital)
    VALUES(current_timestamp(0),inet_client_addr(), inet_server_addr(),TG_OP,NEW.id_cama,NEW.id_hospital);
    return new;
end;
$$;



CREATE FUNCTION public.registrar2() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
    insert into public.log(fecha,ip_origen,ip_destino,operacion,id_cama,id_hospital)
    VALUES(current_timestamp(0),inet_client_addr(), inet_server_addr(),TG_OP,old.id_cama,old.id_hospital);
    return old;
end;
$$;




SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16488)
-- Name: cama; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cama (
    id_cama integer NOT NULL,
    estado character(10) DEFAULT 'desocupada'::bpchar NOT NULL,
    id_hospital integer NOT NULL,
    CONSTRAINT estado_chk CHECK (((estado = 'desocupada'::bpchar) OR (estado = 'ocupada'::bpchar)))
);


--
-- TOC entry 202 (class 1259 OID 16486)
-- Name: cama_id_cama_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.cama ALTER COLUMN id_cama ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.cama_id_cama_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100
    CACHE 1
);


--
-- TOC entry 200 (class 1259 OID 16404)
-- Name: hospital; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hospital (
    id_hospital integer NOT NULL,
    nombre character(30) NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 16407)
-- Name: hospital_id_hospital_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hospital ALTER COLUMN id_hospital ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hospital_id_hospital_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 16501)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
    fecha character varying NOT NULL,
    ip_origen character varying,
    ip_destino character varying,
    operacion character varying NOT NULL,
    id_cama character varying NOT NULL,
    id_hospital character varying NOT NULL
);


--
-- TOC entry 2868 (class 2606 OID 16494)
-- Name: cama id_cama_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cama
    ADD CONSTRAINT id_cama_pk PRIMARY KEY (id_cama);


--
-- TOC entry 2865 (class 2606 OID 16421)
-- Name: hospital id_hospital_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hospital
    ADD CONSTRAINT id_hospital_pk PRIMARY KEY (id_hospital);


--
-- TOC entry 2866 (class 1259 OID 16500)
-- Name: fki_id_hospital_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_id_hospital_fk ON public.cama USING btree (id_hospital);


--
-- TOC entry 2870 (class 2620 OID 16572)
-- Name: cama log_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER log_trigger1 AFTER INSERT OR UPDATE ON public.cama FOR EACH ROW EXECUTE FUNCTION public.registrar1();

CREATE TRIGGER log_trigger2 BEFORE DELETE ON public.cama FOR EACH ROW EXECUTE FUNCTION public.registrar2();


--
-- TOC entry 2869 (class 2606 OID 16495)
-- Name: cama id_hospital_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cama
    ADD CONSTRAINT id_hospital_fk FOREIGN KEY (id_hospital) REFERENCES public.hospital(id_hospital) ON DELETE CASCADE;


-- Completed on 2021-08-18 22:05:35

--
-- PostgreSQL database dump complete
--




--
-- TOC entry 3004 (class 0 OID 16488)
-- Dependencies: 203
-- Data for Name: cama; Type: TABLE DATA; Schema: public; Owner: postgres
--


INSERT INTO public.hospital (nombre) VALUES ('Hospital Nacional de Itagua   ');
INSERT INTO public.hospital (nombre) VALUES ('Hospital de Clinicas          ');
INSERT INTO public.hospital (nombre) VALUES ('Hospital Militar              ');
INSERT INTO public.hospital (nombre) VALUES ('Hospital del Quemado          ');
INSERT INTO public.hospital (nombre) VALUES ('Hospital de Lambare           ');


INSERT INTO public.cama (id_hospital) VALUES (1);
INSERT INTO public.cama (id_hospital) VALUES (1);
INSERT INTO public.cama (id_hospital) VALUES (3);
INSERT INTO public.cama (id_hospital) VALUES (2);
INSERT INTO public.cama (id_hospital) VALUES (3);
INSERT INTO public.cama (id_hospital) VALUES (4);
INSERT INTO public.cama (id_hospital) VALUES (4);
INSERT INTO public.cama (id_hospital) VALUES (5);
INSERT INTO public.cama (id_hospital) VALUES (5);
INSERT INTO public.cama (id_hospital) VALUES (4);


--
-- TOC entry 3001 (class 0 OID 16404)
-- Dependencies: 200
-- Data for Name: hospital; Type: TABLE DATA; Schema: public; Owner: postgres
--

---------prueba log------------

-------------update-----------------------
UPDATE public.cama
    SET estado='ocupada'
    WHERE id_cama=1;
    
UPDATE public.cama
    SET estado='ocupada'
    WHERE id_cama=2;
    
UPDATE public.cama
    SET estado='ocupada'
    WHERE id_cama=3;

UPDATE public.cama
    SET estado='ocupada'
    WHERE id_cama=5;
    
UPDATE public.cama
    SET estado='ocupada'
    WHERE id_cama=7;


-----------insert------------
INSERT INTO public.cama(id_hospital)
    VALUES (3);
    
INSERT INTO public.cama(id_hospital)
    VALUES (5);
    
INSERT INTO public.cama(id_hospital)
    VALUES (4);


-----------delete-----------------

DELETE FROM public.cama
    WHERE id_cama=4;
    
DELETE FROM public.cama
    WHERE id_cama=2;

DELETE FROM public.cama
    WHERE id_cama=6;
    
DELETE FROM public.cama
    WHERE id_cama=7;





