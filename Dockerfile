FROM ubuntu:20.04

# Set environment variable for ADB server
ENV ADB_SERVER_SOCKET=tcp:host.docker.internal:5037

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    openjdk-17-jdk \
    curl \
    unzip \
    python3 \
    python3-pip \
    socat \
    maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Node.js and Appium server
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt-get install -y --no-install-recommends nodejs && \
    npm install -g appium && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Appium drivers
RUN appium driver install uiautomator2

# Download and install the latest platform-tools
RUN curl -sL https://dl.google.com/android/repository/platform-tools-latest-linux.zip -o platform-tools.zip && \
    unzip platform-tools.zip -d /usr/local/android-sdk && \
    rm platform-tools.zip

# Update PATH to include platform-tools
ENV PATH $PATH:/usr/local/android-sdk/platform-tools

# Check adb version to ensure it matches the host version
RUN adb version

# Copy your project files
COPY . /app
WORKDIR /app

# Install Maven dependencies (if using Maven)
RUN mvn clean install

# Expose Appium server port
EXPOSE 4723

# Command to start Appium server in the background and then run TestNG tests
CMD appium & \
    sleep 10 && \
    mvn test