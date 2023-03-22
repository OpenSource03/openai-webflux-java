package com.reactiveclown.openaiwebfluxclient.client.audio;

import com.reactiveclown.openaiwebfluxclient.client.embeddings.CreateEmbeddingsRequest;
import org.springframework.core.io.PathResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AudioServiceImpl implements AudioService {

    private final WebClient client;

    public AudioServiceImpl(WebClient client) {
        this.client = client;
    }

    @Override
    public Mono<CreateTranscriptionResponse> createTranscription(CreateTranscriptionRequest request) {
        String createTranscriptionUrl = "/audio/transcriptions";
        var multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new PathResource(request.file()));
        multipartBodyBuilder.part("model", request.model());
        if (request.prompt() != null) {
            multipartBodyBuilder.part("prompt", request.prompt());
        }
        multipartBodyBuilder.part("response_format", request.responseFormat());
        multipartBodyBuilder.part("temperature", request.temperature());
        if (request.language() != null) {
            multipartBodyBuilder.part("language", request.language());
        }

        return client.post()
                .uri(createTranscriptionUrl)
                .bodyValue(multipartBodyBuilder.build())
                .retrieve()
                .bodyToMono(CreateTranscriptionResponse.class);
    }

    @Override
    public Mono<CreateTranslationResponse> createTranslation(CreateTranslationRequest request) {
        String createTranslationUrl = "/audio/transcriptions";
        var multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new PathResource(request.file()));
        multipartBodyBuilder.part("model", request.model());
        if (request.prompt() != null) {
            multipartBodyBuilder.part("prompt", request.prompt());
        }
        multipartBodyBuilder.part("response_format", request.responseFormat());
        multipartBodyBuilder.part("temperature", request.temperature());

        return client.post()
                .uri(createTranslationUrl)
                .bodyValue(multipartBodyBuilder.build())
                .retrieve()
                .bodyToMono(CreateTranslationResponse.class);
    }
}