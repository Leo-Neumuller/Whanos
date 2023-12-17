# Whanos

## Overview

Automaticaly deploy app

## Features

- Create docker image based on the language

## Prerequisites

- ansible
- ansible-galaxy

## Project setup

### Set the inventory settings in a inventory.yml file

```bash
jenkins:
  hosts:
    webserver01:
      ansible_host: xxx.xxx.xxx.xxx
```

## Getting Started

```bash
# Clone the repository
git clone https://github.com/EpitechPromo2026/B-DOP-500-LIL-5-1-whanos-leo.neumuller.git

# Change into the project directory
cd B-DOP-500-LIL-5-1-whanos-leo.neumuller

# Run the playbook
ansible-playbook -i inventory.yml playbook.yml
```