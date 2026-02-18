# ----------- Build Stage -----------

FROM ghcr.io/graalvm/graalvm-community:25 AS builder

WORKDIR /app

RUN gu install native-image

COPY . .

RUN chmod +x gradlew

# Build native executable

RUN ./gradlew nativeCompile --no-daemon -x test
 
# ----------- Runtime Stage -----------

FROM alpine:3.19

WORKDIR /app

# Install required tools

RUN apk add --no-cache \
    curl \
    busybox-extras \ 
    less \
    libstdc++ \
    libc6-compat

# Copy native binary (adjust name if required)

COPY --from=builder /app/build/native/nativeCompile/* /app/app

# Create non-root user

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

EXPOSE 8080

ENTRYPOINT ["/app/app"]
 
