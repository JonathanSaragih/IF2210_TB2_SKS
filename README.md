# JOJIKANABE - Harvest Game OOP

> **Tugas Besar 2 IF2210 - Pemrograman Berorientasi Objek**

> This program is a simple pixel game using oop and design patterns concepts. The game is about two player compete each
> other farming the crops and animals. The player who has the most money at the end of the game wins.

![logo harvest](./image/os.jpg)

## Table of Contents

1. [Overview](#overview)
2. [Technologies Used](#technologies-used)
3. [Setting Up](#setting-up)
4. [Structure](#structure)
5. [Status](#status)
6. [Ch. 0 - Toolchain, Kernel, GDT](#ch-0---toolchain-kernel-gdt)
7. [Ch. 1 - Interrupt, Driver, File System](#ch-1---interrupt-driver-file-system)
8. [Ch. 2 - Paging, User Mode, Shell](#ch-2---paging-user-mode-shell)
9. [Ch. 3 - Process, Scheduler, Multitasking](#ch-3---process-scheduler-multitasking)
10. [Authors](#created-by)
11. [Acknowledgements](#acknowledgements)

## Overview

This project is the implementation of a simple operating system from scratch. It provides a guided journey through
fundamental OS development concepts and components across multiple chapters, covering topics such as toolchain setup,
kernel creation, interrupt handling, driver development, file system implementation, memory management with paging, and
multitasking support.

Key objectives of this project include:

- Understanding the boot process and how to load an OS using GRUB.
- Developing a simple kernel in C, managing memory, and handling basic hardware interrupts.
- Creating drivers for essential hardware components such as the keyboard and text framebuffer.
- Implementing a FAT32 file system to manage files and directories.
- Enabling paging to support virtual memory.
- Introducing user mode to run applications and creating a basic shell for user interaction.
- Managing processes and implementing a scheduler for multitasking.

## Important Notice

> [!IMPORTANT]\
> Windows users will experience issues if not using `Windows Subsystem for Linux`. For the best experience, it is
> recommended to use WSL or a native Linux installation. Apple Silicon users should follow the alternate toolchain and
> workflow provided specifically for that architecture.

> [!IMPORTANT]\
> There are three versions of this project released :+1:. Make sure you are using the latest version, which has been
> tested thoroughly. Regularly check the project repository for updates to ensure you have the latest fixes and
> improvements.

## Technologies Used

- Java
- Maven
- JavaFX
- CSS

## Setting Up

<details>
<summary>:eyes: Prerequisites</summary>
Before you start, ensure you have the following prerequisites installed on your system:

1. **Operating System:**

    - Linux (preferred), macOS, or Windows with WSL (Windows Subsystem for Linux)

2. **Development Tools:**

    - Netwide Assembler (NASM)
    - GNU C Compiler (GCC)
    - GNU Linker (LD)
    - QEMU
    - System i386
    - GNU Make
    - genisoimage
    - GDB (GNU Debugger)

3. **Additional Software:**

    - Git (for version control)
    - Visual Studio Code or any other code editor

4. **Knowledge Requirements:**
    - Basic understanding of C programming
    - Familiarity with assembly language
    - Understanding of computer architecture and operating system concepts

</details>

<details>
<summary>:eyes: Installation</summary>
Install The Required Dependencies
#### Clone the Repository:

```sh
git clone https://github.com/yourusername/os-project.git
cd os-project
```

#### Install GCC, Binutils, Make:

```sh
sudo apt-get update
sudo apt-get install build-essential bison flex libgmp3-dev libmpc-dev libmpfr-dev texinfo
```

#### Install QEMU:

```sh
sudo apt-get install qemu
```

</details>

<details>
<summary>:eyes: Usage</summary>
Navigate to the source directory:

```sh
cd src
```

Build the OS:

```sh
make run
```

</details>

## Structure

```bash
IF2210_TB2_SKS
┣ .idea
┣ .mvn
┣ .gitignore
┣ mvnw
┣ mvnw.cmd
┣ pom.xml
┣ README.md
┣ src
┃  main
┃ ┃ ┗ java
┃ ┃ ┃ ┗ jojikanabe.if2210_tb2_sks
┃ ┃ ┃ ┃ ┣ controller


```

## Created by

| Name                           | NIM      | Connect                                                |
|--------------------------------|----------|--------------------------------------------------------|
| Jonathan Emmanuel Saragih      | 13522121 | [@JonathanSaragih](https://github.com/JonathanSaragih) |
| Satriadhikara Panji Yudhistira | 13522125 | [@satriadhikara](https://github.com/satriadhikara)     |
| Mohammad Andhika Fadillah      | 13522128 | [@Andhikafdh](https://github.com/Andhikafdh)           |
| Farrel Natha Saskoro           | 13522145 |                                                        |
| Jason Fernando                 | 13522156 |                                                        |

## Acknowledgements

- Tuhan Yang Maha Esa
- Dosen Pengampu Mata Kuliah OOP Institut Teknologi Bandung 2024
- Asisten Mata Kuliah OOP Institut Teknologi Bandung 2024
