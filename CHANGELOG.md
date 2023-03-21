# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## API
The API consists of all public Kotlin types from `com.atlassian.performance.tools.concurrency.api` and its subpackages:

  * [source compatibility]
  * [binary compatibility]
  * [behavioral compatibility] with behavioral contracts expressed via Javadoc

[source compatibility]: http://cr.openjdk.java.net/~darcy/OpenJdkDevGuide/OpenJdkDevelopersGuide.v0.777.html#source_compatibility
[binary compatibility]: http://cr.openjdk.java.net/~darcy/OpenJdkDevGuide/OpenJdkDevelopersGuide.v0.777.html#binary_compatibility
[behavioral compatibility]: http://cr.openjdk.java.net/~darcy/OpenJdkDevGuide/OpenJdkDevelopersGuide.v0.777.html#behavioral_compatibility

## [Unreleased]
[Unreleased]: https://github.com/atlassian/concurrency/compare/release-1.2.1...master

## [1.2.1] - 2023-03-21
[1.2.1]: https://github.com/atlassian/concurrency/compare/release-1.2.0...release-1.2.1

### Fixed
- Drop `log4j-core` and `log4j-slf4j-impl` dependencies from POM. Fix [JPERF-570].

[JPERF-570]: https://ecosystem.atlassian.net/browse/JPERF-570

## [1.2.0] - 2023-03-01
[1.2.0]: https://github.com/atlassian/concurrency/compare/release-1.1.2...release-1.2.0

### Added
- Add `AbruptScheduledExecutorService`.

## [1.1.2] - 2022-06-23
[1.1.2]: https://github.com/atlassian/concurrency/compare/release-1.1.1...release-1.1.2

Empty release to test changes in release process.

## [1.1.1] - 2022-03-02
[1.1.1]: https://github.com/atlassian/concurrency/compare/release-1.1.0...release-1.1.1

### Fixed
- Bump log4j to `2.17.1`. Address JPERF-772.

[JPERF-772]: https://ecosystem.atlassian.net/browse/JPERF-772

## [1.1.0] - 2019-07-31
[1.1.0]: https://github.com/atlassian/concurrency/compare/release-1.0.0...release-1.1.0

### Added
- Add `AbruptExecutorService`. Resolve [JPERF-546].

### Fixed
- Switch to non-deprecated Kotlin stdlib. Fix [JPERF-466].

[JPERF-546]: https://ecosystem.atlassian.net/browse/JPERF-546
[JPERF-466]: https://ecosystem.atlassian.net/browse/JPERF-466

## [1.0.0] - 2018-08-30
[1.0.0]: https://github.com/atlassian/concurrency/compare/release-0.0.2...release-1.0.0

### Changed
- Define the public API.

### Added
- License.

## [0.0.2] - 2018-08-02
[0.0.2]: https://github.com/atlassian/concurrency/compare/release-0.0.1...release-0.0.2

### Fixed
- Improve the release process.

## [0.0.1] - 2018-07-27
[0.0.1]: https://github.com/atlassian/concurrency/compare/initial-commit...release-0.0.1

### Added
- Migrate concurrency tracing and graceful termination from [JPT submodule].
- Add [README.md](README.md).
- Configure Bitbucket Pipelines.

[JPT submodule]: https://stash.atlassian.com/projects/JIRASERVER/repos/jira-performance-tests/browse/concurrency?at=bff5b4bb5e6d057940693b71b6540dad160529bd
