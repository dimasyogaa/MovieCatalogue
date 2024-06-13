package com.yogadimas.moviecatalogue.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.yogadimas.moviecatalogue.data.source.remote.StatusResponse.ERROR;
import static com.yogadimas.moviecatalogue.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {
    @NonNull
    public final StatusResponse status;
    @Nullable
    public final String message;
    @Nullable
    public final T body;

    public ApiResponse(@NonNull StatusResponse status,
                       @Nullable T body,
                       @Nullable String message
    ) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiResponse<T> success(@Nullable T body) {
        return new ApiResponse<>(SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> error(String msg, @Nullable T body) {
        return new ApiResponse<>(ERROR, body, msg);
    }
}
