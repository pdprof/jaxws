FROM ibmcom/websphere-traditional:latest
ENV ENABLE_BASIC_LOGGING=true
COPY was90/PASSWORD /tmp/PASSWORD
COPY --chown=was:root was90/virtual-host.py /work/config/
COPY --chown=was:root was90/apps.py /work/config/
COPY --chown=was:root apps/jaxwsEAR.ear /tmp/jaxwsEAR.ear
RUN /work/configure.sh
