package com.example.authapi.data.services;

import com.example.authapi.data.entities.ProcessingLog;
import com.example.authapi.data.entities.User;
import com.example.authapi.data.repositories.ProcessRepository;
import com.example.authapi.data.repositories.UserRepository;
import com.example.authapi.rest.clients.DataApiClient;
import com.example.authapi.rest.dtos.TransformResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProcessingService {

    private final ProcessRepository processRepository;
    private final DataApiClient dataApiClient;
    private final UserRepository userRepository;

    public TransformResponse process(String text, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        TransformResponse response = dataApiClient.transform(text);

        ProcessingLog processingLog = new ProcessingLog();
        processingLog.setUserId(user.getId());
        processingLog.setInputText(text);
        processingLog.setOutputText(response.getResult());
        processingLog.setCreatedAt(LocalDateTime.now());

        processRepository.save(processingLog);

        return response;
    }

}
