package cn.violin.onenote.service;


import cn.violin.onenote.dao.PageRepo;
import cn.violin.onenote.dao.SectionRepo;
import cn.violin.onenote.dto.StudyTab;
import cn.violin.onenote.entity.Page;
import cn.violin.onenote.entity.QPage;
import cn.violin.onenote.entity.QSection;
import cn.violin.onenote.entity.Section;
import cn.violin.onenote.vo.PageVo;
import cn.violin.onenote.vo.SectionVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OnenoteService {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    private PageRepo pageRepo;

    @Autowired
    private SectionRepo sectionRepo;

    public List<SectionVo> getStudy(long userId) {

        QSection qSection = QSection.section;
        QPage page = QPage.page;
        QBean<StudyTab> qBean = Projections.bean(StudyTab.class,
                qSection.sectionId,
                qSection.sectionName,
                qSection.userId,
                page.pageId,
                page.pageName,
                page.pagePath);

        List<StudyTab> result = jpaQueryFactory.select(qBean)
                .from(qSection, page)
                .where(qSection.userId.eq(userId).and(qSection.sectionId.eq(page.sectionId)))
                .fetch();
        List<SectionVo> sectionVos = result.stream().collect(Collectors.groupingBy(StudyTab::getSectionId))
                .entrySet().stream().map(section -> {
                    long sectionId = section.getKey();
                    List<StudyTab> studyTabs = section.getValue();
                    List<PageVo> pageVos = studyTabs.stream().map(record -> PageVo.builder().pageId(record.getPageId())
                            .pageName(record.getPageName())
                            .sectionId(record.getSectionId())
                            .pagePath(record.getPagePath())
                            .build()).collect(Collectors.toList());
                    return SectionVo.builder().sectionId(sectionId)
                            .sectionName(studyTabs.get(0).getSectionName())
                            .pages(pageVos).build();
                }).collect(Collectors.toList());

        return sectionVos;
    }

    @Transactional()
    public void updatePage(PageVo pageVo) {
        QPage page = QPage.page;
        jpaQueryFactory.update(page)
                .set(page.pageName, pageVo.getPageName())
                .where(page.pageId.eq(pageVo.getPageId()))
                .execute();
    }

    @Transactional()
    public void updateSection(SectionVo sectionVo) {
        QSection qSection = QSection.section;
        jpaQueryFactory.update(qSection)
                .set(qSection.sectionName, sectionVo.getSectionName())
                .where(qSection.sectionId.eq(sectionVo.getSectionId()))
                .execute();
    }

    @Transactional()
    public SectionVo insertSection() {
        Section section = new Section();
        section.setUserId(Long.valueOf(2));
        section.setSectionName("未分类");
        sectionRepo.save(section);
        Page page = new Page();
        page.setSectionId(section.getSectionId());
        page.setPageName("Page1");
        page.setPagePath("text");
        pageRepo.save(page);

        PageVo pageVo = PageVo.builder().sectionId(section.getSectionId())
                .pageId(page.getPageId())
                .pageName(page.getPageName())
                .pagePath(page.getPagePath())
                .build();
        List<PageVo> pageVos = new ArrayList<>();
        pageVos.add(pageVo);

        return SectionVo.builder().sectionId(section.getSectionId())
                .sectionName(section.getSectionName())
                .userId(Long.valueOf(2))
                .pages(pageVos).build();
    }

    @Transactional()
    public PageVo insertPage(PageVo input) {
        Page page = new Page();
        page.setSectionId(input.getSectionId());
        page.setPageName("Page");
        page.setPagePath("text");
        pageRepo.save(page);
        return PageVo.builder().pageId(page.getPageId())
                .sectionId(page.getSectionId())
                .pageName(page.getPageName())
                .pagePath(page.getPagePath())
                .build();
    }
}
