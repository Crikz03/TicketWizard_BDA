/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversiones;

import dtos.EventoDTO;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import objetos.Evento;

/**
 *
 * @author Chris
 */
public class ConvertidorGeneral {

    public static <D, E> E convertidorEntidad(D dto, Class<E> entityClass) {
        try {
            E entidad = entityClass.getDeclaredConstructor().newInstance();

            // Obtener los campos del DTO y de la entidad
            Field[] camposDto = dto.getClass().getDeclaredFields();
            Field[] camposEntidad = entityClass.getDeclaredFields();

            // Iterar sobre los campos y copiar los valores
            for (Field campoDto : camposDto) {
                campoDto.setAccessible(true);
                for (Field campoEntidad : camposEntidad) {
                    campoEntidad.setAccessible(true);
                    if (campoDto.getName().equals(campoEntidad.getName())
                            && campoDto.getType().equals(campoEntidad.getType())) {
                        campoEntidad.set(entidad, campoDto.get(dto));
                        break;
                    } else if (campoDto.getName().equals(campoEntidad.getName())
                            && !campoDto.getType().equals(campoEntidad.getType())) {

                        if (campoDto.getType().getSimpleName().endsWith("DTO")) {
                            Object nestedDto = campoDto.get(dto);
                            if (nestedDto != null) {
                                Object nestedEntity = convertidorEntidad(nestedDto, campoEntidad.getType());
                                campoEntidad.set(entidad, nestedEntity);
                            }
                        }
                    }
                }
            }

            return entidad;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir DTO a entidad", e);
        }
    }

// Método genérico para convertir una Entidad a un DTO (Mismos Nombres)
    public static <E, D> D convertidoraDTO(E entidad, Class<D> claseDto) {
        try {
            D dto = claseDto.getDeclaredConstructor().newInstance();

            // Obtener los campos de la entidad y del DTO
            Field[] camposEntidad = entidad.getClass().getDeclaredFields();
            Field[] camposDto = claseDto.getDeclaredFields();

            // Iterar sobre los campos y copiar los valores
            for (Field campoEntidad : camposEntidad) {
                campoEntidad.setAccessible(true);
                for (Field campoDto : camposDto) {
                    campoDto.setAccessible(true);
                    if (campoEntidad.getName().equals(campoDto.getName())
                            && campoEntidad.getType().equals(campoDto.getType())) {
                        campoDto.set(dto, campoEntidad.get(entidad));
                        break;
                    } else if (campoEntidad.getName().equals(campoDto.getName())
                            && !campoEntidad.getType().equals(campoDto.getType())) {

                        if (campoEntidad.getType().getSimpleName().endsWith("DTO")) {
                            Object nestedEntity = campoEntidad.get(entidad);
                            if (nestedEntity != null) {
                                Object nestedDto = convertidoraDTO(nestedEntity, campoDto.getType());
                                campoDto.set(dto, nestedDto);
                            }
                        }
                    }
                }
            }

            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir entidad a DTO", e);
        }
    }

    public static <D, E> List<E> convertidorListaEntidad(List<D> listaDto, Class<E> entityClass) {
        List<E> listaEntidades = new ArrayList<>();
        for (D dto : listaDto) {
            listaEntidades.add(convertidorEntidad(dto, entityClass));
        }
        return listaEntidades;
    }

    public static <E, D> List<D> convertidoraListaDTO(List<E> listaEntidad, Class<D> claseDto) {
        List<D> listaDtos = new ArrayList<>();
        for (E entidad : listaEntidad) {
            listaDtos.add(convertidoraDTO(entidad, claseDto));
        }
        return listaDtos;
    }
    
    public static EventoDTO convertidorEntidad(Evento evento) {
        if (evento == null) {
            return null; // Maneja el caso nulo
        }

        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setIdEvento(evento.getIdEvento());
        eventoDTO.setNombre(evento.getNombre());
        eventoDTO.setFecha(evento.getFecha());
        eventoDTO.setLocalidad(evento.getLocalidad());
        eventoDTO.setCapacidad(evento.getCapacidad());
        eventoDTO.setVenue(evento.getVenue());
        eventoDTO.setDescripcion(evento.getDescripcion());

        return eventoDTO;
    }

    public static Evento convertidorDTOaEntidad(EventoDTO eventoDTO) {
        if (eventoDTO == null) {
            return null; // Maneja el caso nulo
        }

        Evento evento = new Evento();
        evento.setIdEvento(eventoDTO.getIdEvento());
        evento.setNombre(eventoDTO.getNombre());
        evento.setFecha(eventoDTO.getFecha());
        evento.setLocalidad(eventoDTO.getLocalidad());
        evento.setCapacidad(eventoDTO.getCapacidad());
        evento.setVenue(eventoDTO.getVenue());
        evento.setDescripcion(eventoDTO.getDescripcion());

        return evento;
    }

}
