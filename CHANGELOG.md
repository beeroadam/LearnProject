# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.0] - 2024-01-24
### Added
- Introduces Spring Data JPA to the application. This allows the application to persist data to a database.
- Changed the application to use an H2 database instead of an in-memory database. This allows the application to persist data between restarts.

## [0.1.1] - 2024-01-18
### Added
- Added Renovate Maven build Git workflow. This workflow will automatically create pull requests to update dependencies in `pom.xml` when new versions are available.
- Integrated CodeQL analysis into the GitHub workflow. CodeQL is a powerful analysis engine by GitHub that uses queries to identify potential vulnerabilities in the codebase.

## [0.1.0] - 2024-01-17
### Added
- Initial release
- Basic Application with Spring Core/MVC
- View product catalog (list of products)
- View details of a single product
- Metrics
- Logging

## [0.0.1] - 2024-01-11
### Added
- Project setup
- Initial commit
