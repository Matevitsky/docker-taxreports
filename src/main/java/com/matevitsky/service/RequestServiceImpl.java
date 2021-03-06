package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Request;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.RequestService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RequestServiceImpl implements RequestService {

    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);

    private RequestInspectorChangeRepository requestInspectorChangeRepository;

    public RequestServiceImpl(RequestInspectorChangeRepository requestInspectorChangeRepository) {
        this.requestInspectorChangeRepository = requestInspectorChangeRepository;
    }

    @Override
    public boolean create(Request request) {
        try (Connection connection = ConnectorDB.getConnection()) {
            requestInspectorChangeRepository.create(request, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            requestInspectorChangeRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Request update(Request entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Request> getById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Request>> getAll() {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to all entities" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteByClientID(int clientId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            requestInspectorChangeRepository.deleteByClientID(clientId, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to all entities" + e.getMessage());
            return false;
        }
        return true;
    }
}
