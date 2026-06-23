#!/usr/bin/env bash

if [ ! -f "/opt/allure-2.29.0/bin/allure" ]; then
    echo "--- 🚀 Descargando Allure ---"
    wget -q https://github.com/allure-framework/allure2/releases/download/2.29.0/allure-2.29.0.tgz
    tar -zxf allure-2.29.0.tgz -C /opt/
fi

# Siempre recrear el symlink (no se cachea)
ln -sf /opt/allure-2.29.0/bin/allure /usr/bin/allure

echo "--- 📄 Generando reporte Single HTML ---"
allure generate target/allure-results -o ./allure-temp --clean --single-file