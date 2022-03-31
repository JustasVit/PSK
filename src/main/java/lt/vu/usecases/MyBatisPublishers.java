package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;

import lt.vu.mybatis.dao.PublisherMapper;
import lt.vu.mybatis.model.Publisher;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MyBatisPublishers {

    @Inject
    private PublisherMapper publisherMapper;

    @Getter
    private List<Publisher> allPublishers;

    @Getter @Setter
    private Publisher publisherToCreate = new Publisher();

    @PostConstruct
    public void init() {
        this.loadAllPublishers();
    }

    private void loadAllPublishers() {
        this.allPublishers = publisherMapper.selectAll();
    }

    @Transactional
    public String createPublisher() {
        publisherMapper.insert(publisherToCreate);
        return "/myBatis/publishers?faces-redirect=true";
    }
}
