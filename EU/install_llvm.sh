#!/bin/bash
# Script para instalar LLVM/Clang sem sudo (user space)
#chmod +x install_llvm.sh

# Escolha a versão do LLVM (pode ajustar se quiser outra)
LLVM_VERSION=17.0.6
LLVM_TAR=clang+llvm-$LLVM_VERSION-x86_64-linux-gnu-ubuntu-22.04.tar.xz
LLVM_URL=https://github.com/llvm/llvm-project/releases/download/llvmorg-$LLVM_VERSION/$LLVM_TAR

# Pasta de instalação local
INSTALL_DIR=$HOME/llvm

echo " baixando LLVM $LLVM_VERSION..."
mkdir -p $INSTALL_DIR
wget $LLVM_URL -O /tmp/$LLVM_TAR

echo "extraindo..."
tar -xf /tmp/$LLVM_TAR -C $INSTALL_DIR --strip-components=1

echo "ajustando PATH..."
echo "export PATH=$INSTALL_DIR/bin:\$PATH" >> $HOME/.bashrc
export PATH=$INSTALL_DIR/bin:$PATH

echo "Instalação concluída!"
echo "Agora você pode usar clang, llc e llvm-as sem sudo."
echo "Exemplo: clang --version"
