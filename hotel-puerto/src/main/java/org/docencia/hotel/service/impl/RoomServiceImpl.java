package org.docencia.hotel.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Set<Room> findAll() {
        return roomMapper.toDomain(new HashSet<>(roomRepository.findAll()));
    }

    @Override
    public Room findById(long id) {
        return roomMapper.toDomain(roomRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Room save(Room room) {
        if (room.getId() == null) {
            room.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return roomMapper.toDomain(roomRepository.save(roomMapper.toEntity(room)));
    }

    @Override
    @Transactional
    public Boolean deleteById(long id) {
        if (!roomRepository.existsById(id)) {
            return false;
        }
        roomRepository.deleteById(id);
        return true;
    }

    @Override
    public Room findByHotelId(Long hotelId) {
        return roomMapper.toDomain(roomRepository.findByHotelId(hotelId).get(0));
    }
}
